package com.nuzhd.controller;

import com.nuzhd.dto.TransformationDto;
import com.nuzhd.service.ImageService;
import com.nuzhd.service.impl.MarvinTransformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping("/images")
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final MarvinTransformationService marvinTransformationService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile file) {
        imageService.uploadImage(file);
        return ResponseEntity
                .ok()
                .body("Successfully uploaded %s".formatted(file.getOriginalFilename()));
    }

    @GetMapping(value = "/{fileName}", produces = IMAGE_JPEG_VALUE)
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
        var image = imageService.downloadImage(fileName);
        return ResponseEntity
                .ok()
                .body(image);
    }

    @PostMapping("/{id}/transform")
    public ResponseEntity<?> transformImage(@PathVariable("id") String filename,
                                            @RequestBody TransformationDto transformations) {
        marvinTransformationService.applyTransformationsAndSave(filename, transformations);
        return ResponseEntity.ok().build();
    }

}
