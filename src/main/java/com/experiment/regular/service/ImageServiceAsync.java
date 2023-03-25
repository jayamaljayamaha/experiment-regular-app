package com.experiment.regular.service;

import com.experiment.regular.common.Device;
import com.experiment.regular.common.ImageData;
import com.experiment.regular.common.InvalidImage;
import com.experiment.regular.common.ReturnImage;
import com.experiment.regular.dto.ImageRequest;
import com.experiment.regular.dto.ImageResponse;
import com.experiment.regular.entity.Image;
import com.experiment.regular.exception.exceptions.InvalidImageException;
import com.experiment.regular.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public class ImageServiceAsync implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public ImageResponse saveImages(ImageRequest imageRequest) {
        ImageResponse response = ImageResponse.builder().build();
        List<CompletableFuture<Void>> futures = imageRequest.getImages().stream().map(imageData ->
                CompletableFuture.supplyAsync(() -> getImageData(response, imageRequest, imageData), taskExecutor)
                        .thenApply(this::createImageEntity)
                        .thenAccept(image -> createResponse(response, image))
                        .exceptionally(thorwable -> {
                            InvalidImageException invalidImageException = (InvalidImageException) thorwable.getCause();
                            response.getInvalidImages().addAll(invalidImageException.getInvalidImageList());
                            return null;
                        })).toList();

        futures.forEach(CompletableFuture::join);
        return response;
    }

    private void createResponse(ImageResponse response, Image image) {
        try {
            Image savedImage = imageRepository.save(image);
            response.getImages().add(ReturnImage.builder()
                    .id(savedImage.getId())
                    .url(savedImage.getUrl())
                    .name(savedImage.getName())
                    .build());
            response.setSuccessImages(response.getSuccessImages() + 1);
        } catch (Exception exception) {
            throw new InvalidImageException(List.of(InvalidImage.builder()
                            .error("Db exception occured")
                    .build()));
        }

    }

    private ImageData getImageData(ImageResponse response, ImageRequest imageRequest, ImageData imageData) {
        Set<ConstraintViolation<ImageData>> violations = validator.validate(imageData);
        if (!violations.isEmpty()) {
            response.setFailedImages(response.getFailedImages() + 1);
            List<InvalidImage> invalidImages = violations.stream()
                    .map(violation -> InvalidImage.builder().property(violation.getPropertyPath().toString())
                            .imageIndex(imageRequest.getImages().indexOf(imageData)).error(violation.getMessage()).build()).toList();
            throw new InvalidImageException(invalidImages);
        }
        return imageData;
    }

    private Image createImageEntity(ImageData imageData) {
        return Image.builder()
                .name(imageData.getName())
                .url(imageData.getUrl())
                .width(imageData.getWidth())
                .height(imageData.getHeight())
                .numberOfPixels(imageData.getNumberOfPixels())
                .format(imageData.getFormat())
                .createdDate(imageData.getCreatedDate())
                .lastModifiedDate(imageData.getLastModifiedDate())
                .size(imageData.getSize())
                .capturedBy(imageData.getCapturedBy())
                .device(Device.valueOf(imageData.getDevice()))
                .build();
    }
}
