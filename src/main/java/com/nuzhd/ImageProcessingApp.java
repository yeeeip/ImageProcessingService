package com.nuzhd;

import io.minio.MinioClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageProcessingApp {
    public static void main(String[] args) {
        SpringApplication.run(ImageProcessingApp.class, args);
    }

    private final MinioClient minioClient;

    public ImageProcessingApp(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

}