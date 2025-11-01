package com.nuzhd.dto;

import com.nuzhd.dto.constants.TransformationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class FiltersTransformation extends Transformation {

    private List<String> filters;

    @Override
    public TransformationType getType() {
        return TransformationType.FILTERS;
    }
}
