package com.github.blogw.event4springboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MService implements Serializable {
    private String serviceClass;

    private String serviceName;

    private String serviceLevel;

    private Integer dispOrder;

    private Byte isAaaOriginal;

    private Byte deleted;

    private Long serialAt;

    private Long createdBy;

    private String createdWith;

    private Date createdAt;

    private Long updatedBy;

    private String updatedWith;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}