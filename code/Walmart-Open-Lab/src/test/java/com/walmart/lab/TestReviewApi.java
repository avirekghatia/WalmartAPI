package com.walmart.lab;

import com.walmart.lab.api.ApiFactory;
import com.walmart.lab.conf.ApiConstants;
import com.walmart.lab.exceptions.WalmartException;
import com.walmart.lab.response.ApiResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by sourabhtaletiya on 1/10/16.
 */
public class TestReviewApi extends TestWalmartApiBase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        baseApi = ApiFactory.getInstance(ApiConstants.REVIEWS, apiKey);
    }


    public void test_happy_cases() throws InterruptedException, ExecutionException, IOException, WalmartException {
        ApiResponse apiResponse;
        for (String itemId : itemIdList) {
            apiResponse = baseApi.getResponse(itemId);

            //Search Response is populated
            assertNotNull(apiResponse.getReviewResponse());
            //Other Responses should be NULL
            assertNull(apiResponse.getSearchResponse());
            assertNull(apiResponse.getProductRecommendationResponses());

        }

        apiResponse = baseApi.getResponse("43156930");
        assertEquals(apiResponse.getReviewResponse().getItemId(), 43156930);
        assertNotNull(apiResponse.getReviewResponse().getName());
        assertNotNull(apiResponse.getReviewResponse().getBrandName());
        assertNotNull(apiResponse.getReviewResponse().getProductTrackingUrl());
        assertNotNull(apiResponse.getReviewResponse().getProductTrackingUrl());
        assertNotNull(apiResponse.getReviewResponse().getReviews());
        assertTrue(apiResponse.getReviewResponse().getReviews().size() > 0);
        assertNotNull(apiResponse.getReviewResponse().getReviewStatistics());
        assertNotNull(apiResponse.getReviewResponse().getUpc());
        assertNotNull(apiResponse.getReviewResponse().getCategoryPath());

    }

    public void test_walmart_exception() {

        String itemId = null;
        try {
            baseApi.getResponse(itemId);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        itemId = "4345645-7836742";
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


}
