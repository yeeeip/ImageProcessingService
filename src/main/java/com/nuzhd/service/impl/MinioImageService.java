package com.nuzhd.service.impl;

import com.nuzhd.dto.ImageUploadResult;
import com.nuzhd.service.ImageService;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class MinioImageService implements ImageService {

    private final MinioClient minioClient;

    public MinioImageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public ImageUploadResult uploadImage(MultipartFile file) {
        try {
            var putImage = PutObjectArgs.builder()
                                        .bucket("test")
                                        .object(file.getOriginalFilename())
                                        .stream(file.getInputStream(), file.getSize(), -1)
                                        .build();
            log.info("Uploading image to MinIO: {}", putImage.object());
            var response = minioClient.putObject(putImage);
            log.info("Successfully uploaded file into bucket: {}", response.bucket());
            return new ImageUploadResult(false, file.getName(), response);
        } catch (IOException | MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            log.error("Error while uploading file: {}\n{}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] downloadImage(String fileName) {

        try {
            var image = minioClient.getObject(
                    GetObjectArgs.builder()
                                 .bucket("test")
                                 .object(fileName)
                                 .build()
            );

            return image.readAllBytes();
        } catch (IOException | MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            log.error("Error while downloading file: {}\n{}", e.getMessage(), e.getStackTrace());
            throw new RuntimeException(e);
        }
    }

    @Override
    public GetObjectResponse findImage(String fileName) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.builder()
                                 .bucket("test")
                                 .object(fileName)
                                 .build()
            );
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            log.error("Error occurred: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public StatObjectResponse imageExists(String fileName) {
        try {
            return minioClient.statObject(
                    StatObjectArgs.builder()
                                  .bucket("test")
                                  .object(fileName)
                                  .build()
            );
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException | IOException e) {
            log.error("Error occurred: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
