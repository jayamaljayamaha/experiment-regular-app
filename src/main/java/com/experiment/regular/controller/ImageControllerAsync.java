package com.experiment.regular.controller;

import com.experiment.regular.dto.ImageRequest;
import com.experiment.regular.dto.ImageResponse;
import com.experiment.regular.entity.Image;
import com.experiment.regular.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images/async")
public class ImageControllerAsync {

    @Autowired
    @Qualifier("imageServiceAsync")
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResponse> saveImagesAsync(@RequestBody ImageRequest imageRequest){
        ImageResponse response = imageService.saveImages(imageRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
