package com.experiment.regular.dto;

import com.experiment.regular.common.ImageData;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class ImageRequest {
    private List<ImageData> images;
}
