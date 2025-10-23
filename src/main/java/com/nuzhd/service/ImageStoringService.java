package com.nuzhd.service;

import com.nuzhd.dto.ImageUploadResult;
import org.springframework.web.multipart.MultipartFile;

public interface ImageStoringService {

    ImageUploadResult uploadImage(MultipartFile file);

    void downloadImage();

}
