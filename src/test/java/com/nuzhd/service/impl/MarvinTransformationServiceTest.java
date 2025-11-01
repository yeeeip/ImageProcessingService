package com.nuzhd.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = MarvinTransformationService.class)
class MarvinTransformationServiceTest {

    @Autowired
    private MarvinTransformationService marvinTransformationService;

    @Test
    void testCropImage() {

    }

}