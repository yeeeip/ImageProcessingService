package com.nuzhd.dto;

import com.nuzhd.dto.constants.TransformationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RotateTransformation extends Transformation {

    private Integer degrees;

    @Override
    public TransformationType getType() {
        return TransformationType.ROTATE;
    }
}
