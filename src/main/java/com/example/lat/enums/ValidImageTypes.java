package com.example.lat.enums;

import lombok.Getter;

@Getter
public enum ValidImageTypes {
    PNG("PNG"),
    JPG("JPG"),
    JPEG("JPEG"),
    WEBP("WEBP");

    ValidImageTypes(String imageType) {
        this.imageType = imageType;
    }

    private final String imageType;
}
