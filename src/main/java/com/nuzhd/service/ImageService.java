package com.nuzhd.service;

import com.nuzhd.dto.ImageUploadResult;
import io.minio.GetObjectResponse;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.StatObjectResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    ImageUploadResult uploadImage(MultipartFile file);

    ObjectWriteResponse uploadImage(PutObjectArgs args);

    byte[] downloadImage(String fileName);

    GetObjectResponse findImage(String fileName);

    StatObjectResponse imageExists(String fileName);
}
