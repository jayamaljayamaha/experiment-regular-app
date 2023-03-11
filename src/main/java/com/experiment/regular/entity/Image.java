package com.experiment.regular.entity;

import com.experiment.regular.common.Device;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "image")
@Data
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double width;
    private Double height;
    @Column(name = "number_of_pixels")
    private Integer numberOfPixels;
    private String format;
    private String url;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
    private Integer size;
    @Column(name = "captured_by")
    private String capturedBy;
    @Enumerated(EnumType.STRING)
    private Device device;
}
