package com.nuzhd.service.impl;

import com.nuzhd.dto.CropTransformation;
import com.nuzhd.dto.FiltersTransformation;
import com.nuzhd.dto.FormatTransformation;
import com.nuzhd.dto.ResizeTransformation;
import com.nuzhd.dto.RotateTransformation;
import com.nuzhd.dto.Transformation;
import com.nuzhd.dto.TransformationDto;
import com.nuzhd.service.ImageService;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import marvin.image.MarvinImage;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.nuzhd.utils.MarvinImageUtils.applyFilters;
import static com.nuzhd.utils.MarvinImageUtils.changeFormat;
import static com.nuzhd.utils.MarvinImageUtils.convertToInputStream;
import static com.nuzhd.utils.MarvinImageUtils.cropImage;
import static com.nuzhd.utils.MarvinImageUtils.resizeImage;
import static com.nuzhd.utils.MarvinImageUtils.rotateImage;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarvinTransformationService {

    private final ImageService imageService;

    public ObjectWriteResponse applyTransformationsAndSave(String fileName,
                                                           TransformationDto transformationDto) {
        var imgStats = imageService.imageExists(fileName);

        if (imgStats != null) {

            try {
                var image = imageService.downloadImage(fileName);

                var marvinImage = new MarvinImage(ImageIO.read(new ByteArrayInputStream(image)));

                for (var transformation : extractTransformations(transformationDto)) {
                    marvinImage = transformImage(marvinImage, transformation);
                }

                var stream = convertToInputStream(marvinImage);

                return imageService.uploadImage(PutObjectArgs.builder()
                                                             .bucket("test")
                                                             .stream(stream, stream.available(), -1)
                                                             .build());
            } catch (IOException e) {
                log.error("Error during transforming image: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    private MarvinImage transformImage(MarvinImage image, Transformation transformation) {
        return switch (transformation) {
            case CropTransformation crop -> cropImage(image, crop);
            case ResizeTransformation resize -> resizeImage(image, resize);
            case FormatTransformation format -> changeFormat(image, format);
            case RotateTransformation rotate -> rotateImage(image, rotate);
            case FiltersTransformation filters -> applyFilters(image, filters);
            case null, default -> {
                log.error("Invalid transformation type/null: {}", transformation);
                yield image;
            }
        };
    }

    private List<Transformation> extractTransformations(TransformationDto transformationDto) {
        return Arrays.asList(transformationDto.crop(),
                             transformationDto.resize(),
                             new RotateTransformation(transformationDto.rotate()),
                             new FiltersTransformation(transformationDto.filters()),
                             new FormatTransformation(transformationDto.format())
        );
    }

}
