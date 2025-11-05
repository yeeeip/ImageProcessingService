package com.nuzhd.dto;

import com.nuzhd.dto.constants.TransformationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FormatTransformation extends Transformation {

    private String targetFormat;

    @Override
    public TransformationType getType() {
        return TransformationType.FORMAT;
    }

}
