package com.nuzhd.utils;

import com.nuzhd.dto.CropTransformation;
import com.nuzhd.dto.FiltersTransformation;
import com.nuzhd.dto.FormatTransformation;
import com.nuzhd.dto.ResizeTransformation;
import com.nuzhd.dto.RotateTransformation;
import lombok.extern.slf4j.Slf4j;
import marvin.image.MarvinImage;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class MarvinImageUtils {

    public static MarvinImage cropImage(MarvinImage image, CropTransformation cropTransformation) {
        return image.subimage(
                cropTransformation.getX(),
                cropTransformation.getY(),
                cropTransformation.getWidth(),
                cropTransformation.getHeight()
        );
    }

    public static MarvinImage resizeImage(MarvinImage image, ResizeTransformation resizeTransformation) {
        image.resize(resizeTransformation.getWidth(), resizeTransformation.getHeight());
        return image;
    }

    public static MarvinImage changeFormat(MarvinImage image, FormatTransformation formatTransformation) {
        return image;
    }

    public static MarvinImage rotateImage(MarvinImage image, RotateTransformation rotateTransformation) {
        return image;
    }

    public static MarvinImage applyFilters(MarvinImage image, FiltersTransformation filtersTransformation) {
        return image;
    }

    public static InputStream convertToInputStream(MarvinImage marvinImage) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(marvinImage.getBufferedImage(), marvinImage.getFormatName(), os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            log.error("Error while converting MarvinImage to InputStream: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
