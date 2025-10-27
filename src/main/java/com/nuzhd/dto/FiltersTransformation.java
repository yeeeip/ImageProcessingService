package com.nuzhd.dto;

import com.nuzhd.dto.constants.TransformationType;
import lombok.Getter;

import java.util.List;

@Getter
public class FiltersTransformation extends Transformation {

    private List<String> filters;

    @Override
    public TransformationType getType() {
        return TransformationType.FILTERS;
    }
}
