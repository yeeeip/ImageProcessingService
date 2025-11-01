package com.nuzhd.dto;

import com.nuzhd.dto.constants.TransformationType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormatTransformation extends Transformation {

    private String format;

    @Override
    public TransformationType getType() {
        return TransformationType.FORMAT;
    }

}
