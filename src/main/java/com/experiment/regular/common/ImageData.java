package com.experiment.regular.common;

import com.experiment.regular.annotation.annotations.DeviceValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ImageData {
    @NotBlank(message = "Image name should not be null or empty")
    @Schema(example = "cute dog", type = "string")
    private String name;
    @Digits(integer = 10, fraction = 0)
    @Schema(example = "120", type = "integer")
    private Double width;
    @Digits(integer = 10, fraction = 0)
    @Schema(example = "120", type = "integer")
    private Double height;
    @JsonProperty(value = "number_of_pixels")
    @Digits(integer = 10, fraction = 0)
    @Schema(example = "14400", type = "integer")
    private Integer numberOfPixels;
    @Schema(example = "jpg", type = "string")
    private String format;
    @Schema(example = "https://images.examples.com/cute_dog_123", type = "string")
    private String url;
    @JsonProperty(value = "created_date")
    @Schema(example = "2017-07-21T17:32:28Z", type = "date-time")
    private Date createdDate;
    @JsonProperty(value = "last_modified_date")
    @Schema(example = "2018-07-21T17:32:28Z", type = "date-time")
    private Date lastModifiedDate;
    @Digits(integer = 10, fraction = 0)
    @Schema(example = "1320000", type = "integer")
    private Integer size;
    @JsonProperty(value = "captured_by")
    @Schema(example = "sandun", type = "string")
    private String capturedBy;
    @DeviceValidator
    @Schema(example = "DSLR", type = "string")
    private String device;
}
