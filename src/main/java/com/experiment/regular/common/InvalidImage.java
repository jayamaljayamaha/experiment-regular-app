package com.experiment.regular.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvalidImage {
    private Integer imageIndex;
    private String property;
    private String error;
}
