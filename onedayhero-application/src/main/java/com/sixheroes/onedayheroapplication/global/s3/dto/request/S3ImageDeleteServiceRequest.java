package com.sixheroes.onedayheroapplication.global.s3.dto.request;

public record S3ImageDeleteServiceRequest(
        Long imageId,
        String uniqueName
) {
}
