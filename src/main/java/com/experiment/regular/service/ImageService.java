package com.experiment.regular.service;

import com.experiment.regular.dto.ImageRequest;
import com.experiment.regular.dto.ImageResponse;

public interface ImageService {
    ImageResponse saveImages(ImageRequest imageRequest);
}
