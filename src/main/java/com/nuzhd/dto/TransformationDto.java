package com.nuzhd.dto;

import java.util.List;

public record TransformationDto(
        ResizeTransformation resize,
        CropTransformation crop,
        Integer rotate,
        String format,
        List<String> filters
) {

}
