package com.experiment.regular.dto;

import com.experiment.regular.common.InvalidImage;
import com.experiment.regular.common.ReturnImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class ImageResponse implements Serializable {
    private int successImages;
    private int failedImages;
    @Builder.Default
    private List<ReturnImage> images = new ArrayList<>();
    @Builder.Default
    private List<InvalidImage> invalidImages = new ArrayList<>();

}
