package com.experiment.regular.controller;

import com.experiment.regular.dto.ImageRequest;
import com.experiment.regular.dto.ImageResponse;
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
@RequestMapping(value = "/images")
public class ImageControllerRegular {

    @Autowired
    @Qualifier("imageServiceRegular")
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResponse> saveImages(@RequestBody ImageRequest imageRequest){
        ImageResponse response = imageService.saveImages(imageRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
