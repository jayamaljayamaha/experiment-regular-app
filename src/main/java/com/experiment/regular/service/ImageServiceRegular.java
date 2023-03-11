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
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ImageServiceRegular implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private Validator validator;

    @Override
    public ImageResponse saveImages(ImageRequest imageRequest) {
        ImageResponse response = ImageResponse.builder().build();
        imageRequest.getImages().stream()
                .filter(imageData -> {
                    Set<ConstraintViolation<ImageData>> violations = validator.validate(imageData);
                    if (!violations.isEmpty()) {
                        response.setFailedImages(response.getFailedImages() + 1);
                        response.getInvalidImages().addAll(violations.stream()
                                .map(violation -> InvalidImage.builder().property(violation.getPropertyPath().toString())
                                        .imageIndex(imageRequest.getImages().indexOf(imageData)).error(violation.getMessage()).build()).toList());
                    }
                    return violations.isEmpty();
                })
                .map(this::createImageEntity).forEach(image -> {
                    Image savedImage = imageRepository.save(image);
                    response.getImages().add(ReturnImage.builder()
                            .id(savedImage.getId())
                            .url(savedImage.getUrl())
                            .name(savedImage.getName())
                            .build());
                    response.setSuccessImages(response.getSuccessImages() + 1);
                });
        return response;
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
