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
public class TestSearchApi extends TestWalmartApiBase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        baseApi = ApiFactory.getInstance(ApiConstants.SEARCH, apiKey);
    }


    public void test_happy_cases() throws InterruptedException, ExecutionException, IOException, WalmartException {
        ApiResponse apiResponse;
        for (String searchQuery : searchList) {
            apiResponse = baseApi.getResponse(searchQuery);

            //Whether passed response is for same query or not
            assertEquals(apiResponse.getSearchResponse().getQuery(), searchQuery);

            //Search Response is populated
            assertNotNull(apiResponse.getSearchResponse());

            //Other Responses should be NULL
            assertNull(apiResponse.getReviewResponse());
            assertNull(apiResponse.getProductRecommendationResponses());

            assertTrue(apiResponse.getSearchResponse().getNumItems() > 0);
            assertTrue(apiResponse.getSearchResponse().getNumItems() <= 10);


        }

        apiResponse = baseApi.getResponse("clock");
        assertEquals(apiResponse.getSearchResponse().getNumItems(), apiResponse.getSearchResponse().getItems().size());
        assertTrue(apiResponse.getSearchResponse().getTotalResults() > 1000);

    }


    public void test_different_input() throws InterruptedException, ExecutionException, IOException, WalmartException {
        String query = "Hello123";
        ApiResponse apiResponse = null;

        query = "Hello world!! I saw a saw in a see saw";
        apiResponse = baseApi.getResponse(query);
        assertEquals(apiResponse.getSearchResponse().getQuery(), query);
        assertNotNull(apiResponse.getSearchResponse());
        assertNull(apiResponse.getReviewResponse());
        assertNull(apiResponse.getProductRecommendationResponses());

        query = "1234567890";
        apiResponse = baseApi.getResponse(query);
        assertEquals(apiResponse.getSearchResponse().getQuery(), query);
        assertNotNull(apiResponse.getSearchResponse());
        assertNull(apiResponse.getReviewResponse());
        assertNull(apiResponse.getProductRecommendationResponses());

        query = "Walmart Ecommerce at reston Virginia ";
        apiResponse = baseApi.getResponse(query);
        assertEquals(apiResponse.getSearchResponse().getQuery(), query);
        assertNotNull(apiResponse.getSearchResponse());
        assertNull(apiResponse.getReviewResponse());
        assertNull(apiResponse.getProductRecommendationResponses());

        query = "\"~!@#$%^&*+_)(*&^%$#@~<>?:'''{}{}{\\\"\\\"\\\"\"";
        apiResponse = baseApi.getResponse(query);
        assertEquals(apiResponse.getSearchResponse().getQuery(), query);
        assertNotNull(apiResponse.getSearchResponse());
        assertNull(apiResponse.getReviewResponse());
        assertNull(apiResponse.getProductRecommendationResponses());

        query = "       ipad";
        apiResponse = baseApi.getResponse(query);
        assertEquals(apiResponse.getSearchResponse().getQuery(), query);
        assertNotNull(apiResponse.getSearchResponse());
        assertNull(apiResponse.getReviewResponse());
        assertNull(apiResponse.getProductRecommendationResponses());


    }

    public void test_walmart_exception() {

        String query = null;
        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "";
        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "                ";
        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "     198787 calulator";
        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "-09863146";
        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "0X723684ab";
        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "cycle&format=xml";
        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "'''ipad7823 ";
        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "\"cycle&format=xml&query=clock";
        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "\\\\search\\\\query=clock";
        try {
            baseApi.getResponse(query);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

    }

}
