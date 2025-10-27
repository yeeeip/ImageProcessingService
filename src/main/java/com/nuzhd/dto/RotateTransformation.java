package com.nuzhd.dto;

import com.nuzhd.dto.constants.TransformationType;
import lombok.Getter;

@Getter
public class RotateTransformation extends Transformation {

    private Integer degrees;

    @Override
    public TransformationType getType() {
        return TransformationType.ROTATE;
    }
}
