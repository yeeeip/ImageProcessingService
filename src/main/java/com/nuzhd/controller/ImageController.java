package com.nuzhd.controller;

import com.nuzhd.service.ImageStoringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageStoringService imageStoringService;

    public ImageController(ImageStoringService imageStoringService) {
        this.imageStoringService = imageStoringService;
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile file) {
        imageStoringService.uploadImage(file);
        return ResponseEntity
                .ok()
                .body("Successfully uploaded %s".formatted(file.getOriginalFilename()));
    }

    @GetMapping(value = "/{fileName}", produces = IMAGE_JPEG_VALUE)
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
        var image = imageStoringService.downloadImage(fileName);
        return ResponseEntity
                .ok()
                .body(image);
    }

}
