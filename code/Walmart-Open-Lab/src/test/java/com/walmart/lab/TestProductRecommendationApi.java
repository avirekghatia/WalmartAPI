package com.walmart.lab;

import com.walmart.lab.api.ApiFactory;
import com.walmart.lab.conf.ApiConstants;
import com.walmart.lab.exceptions.WalmartException;
import com.walmart.lab.response.ApiResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Avirek Ghatia on 1/10/16.
 */
public class TestProductRecommendationApi extends TestWalmartApiBase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        baseApi = ApiFactory.getInstance(ApiConstants.PRODUCT_RECOMMENDATION, apiKey);
    }


    public void test_happy_cases() throws InterruptedException, ExecutionException, IOException, WalmartException {
        ApiResponse apiResponse;
        for (String itemId : itemIdList) {
            apiResponse = baseApi.getResponse(itemId);


            //Search Response is populated
            assertNotNull(apiResponse.getProductRecommendationResponses());

            //Other Responses should be NULL
            assertNull(apiResponse.getReviewResponse());
            assertNull(apiResponse.getSearchResponse());


        }

    }

    public void test_walmart_exception() {

        String itemId = null;
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        itemId = "";
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }


        itemId = "          ";
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        itemId = "Hello1234";
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        itemId = "jhdkfhjksduiwe8923478923487324";
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        itemId = "Betty Bought butter but butter was bit buttery";
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        itemId = "43156930 ";
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        itemId = "-1234567";
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        itemId = "0x389798";
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        itemId = "~!@#$%^&*()";
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }


    }

    public void test_coverage() throws InterruptedException, ExecutionException, IOException, WalmartException {
        ApiResponse apiResponse;
        apiResponse = baseApi.getResponse("43156930");
        assertNotNull(apiResponse);
        assertNotNull(apiResponse.getProductRecommendationResponses());
        assertTrue(apiResponse.getProductRecommendationResponses().length > 10);
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getItemId());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getName());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getBrandName());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getProductTrackingUrl());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getProductUrl());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getParentItemId());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getCategoryPath());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getSalePrice());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getAddToCartUrl());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getCustomerRating());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getShortDescription());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getLongDescription());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getUpc());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getModelNumber());
        assertNotNull(apiResponse.getProductRecommendationResponses()[0].getStock());


    }


}
