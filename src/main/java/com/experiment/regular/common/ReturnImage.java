package com.experiment.regular.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ReturnImage implements Serializable{
    private Long id;
    private String name;
    private String url;
}
