package com.nuzhd.dto;

import com.nuzhd.dto.constants.TransformationType;
import lombok.Getter;

@Getter
public class CropTransformation extends Transformation {

    private Integer width;
    private Integer height;
    private Integer x;
    private Integer y;

    @Override
    public TransformationType getType() {
        return TransformationType.CROP;
    }
}
