package com.nuzhd.dto;

import com.nuzhd.dto.constants.TransformationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FiltersTransformation extends Transformation {

    private List<String> filters;

    @Override
    public TransformationType getType() {
        return TransformationType.FILTERS;
    }
}
