package com.sixheroes.onedayheroapplication.review;

import com.sixheroes.onedayheroapplication.global.s3.S3ImageDeleteService;
import com.sixheroes.onedayheroapplication.global.s3.S3ImageUploadService;
import com.sixheroes.onedayheroapplication.global.s3.dto.request.S3ImageUploadServiceRequest;
import com.sixheroes.onedayheroapplication.review.reqeust.ReviewCreateServiceRequest;
import com.sixheroes.onedayheroapplication.review.response.ReviewResponse;
import com.sixheroes.onedayherodomain.review.repository.ReviewImageRepository;
import com.sixheroes.onedayherodomain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final S3ImageUploadService s3ImageUploadService;
    private final S3ImageDeleteService s3ImageDeleteService;

    public ReviewResponse create(
            ReviewCreateServiceRequest request,
            Optional<List<S3ImageUploadServiceRequest>> imageUploadRequests
    ) {
        throw new UnsupportedOperationException();
    }
}
