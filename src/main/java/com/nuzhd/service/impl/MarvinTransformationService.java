package com.nuzhd.service.impl;

import com.nuzhd.dto.CropTransformation;
import com.nuzhd.dto.FormatTransformation;
import com.nuzhd.dto.ResizeTransformation;
import com.nuzhd.dto.Transformation;
import marvin.image.MarvinImage;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;

@Service
public class MarvinTransformationService {

    public MarvinImage applyTransformations(BufferedImage image, List<Transformation> transformations) {
        var marvinImage = new MarvinImage(image);

        for (Transformation transformation : transformations) {
            marvinImage = transformImage(marvinImage, transformation);
        }

        return marvinImage;
    }

    private MarvinImage transformImage(MarvinImage image, Transformation transformation) {
        return switch (transformation) {
            case CropTransformation c -> cropImage(image, c);
            case ResizeTransformation r -> {
                resizeImage(image, r);
                yield image;
            }
            case FormatTransformation f -> changeFormat(image);
            case null, default -> throw new IllegalArgumentException("Unknown transformation: " + transformation);
        };
    }

    private MarvinImage cropImage(MarvinImage image, CropTransformation crop) {
        return image.subimage(crop.getX(), crop.getY(), crop.getWidth(), crop.getHeight());
    }

    private void resizeImage(MarvinImage image, ResizeTransformation resize) {
        image.resize(resize.getWidth(), resize.getHeight());
    }

    private MarvinImage changeFormat(MarvinImage image) {
        return image;
    }

}
