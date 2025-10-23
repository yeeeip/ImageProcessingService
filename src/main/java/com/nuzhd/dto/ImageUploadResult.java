package com.nuzhd.dto;

import io.minio.ObjectWriteResponse;

public record ImageUploadResult(Boolean isError, String fileName, ObjectWriteResponse response, String errorMsg) {

    public ImageUploadResult(Boolean isError, String fileName, ObjectWriteResponse response) {
        this(isError, fileName, response, null);
    }

    public ImageUploadResult(Boolean isError, String errorMsg) {
        this(isError, null, null, errorMsg);
    }

}
