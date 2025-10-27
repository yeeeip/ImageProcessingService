package com.nuzhd.service;

import com.nuzhd.dto.Transformation;

import java.awt.image.BufferedImage;
import java.util.List;

public interface ImageTransformationService {

    BufferedImage applyTransformations(BufferedImage image, List<Transformation> transformations);

}
