package com.nuzhd.dto.constants;

import lombok.Getter;

@Getter
public enum TransformationType {

    CROP("crop"),
    RESIZE("resize"),
    FILTERS("filters"),
    ROTATE("rotate"),
    FORMAT("format");

    private final String value;

    TransformationType(String value) {
        this.value = value;
    }
}
