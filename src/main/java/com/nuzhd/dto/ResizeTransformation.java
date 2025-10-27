package com.nuzhd.dto;

import com.nuzhd.dto.constants.TransformationType;
import lombok.Getter;

@Getter
public class ResizeTransformation extends Transformation{

    private Integer width;
    private Integer height;

    @Override
    public TransformationType getType() {
        return TransformationType.RESIZE;
    }

}
