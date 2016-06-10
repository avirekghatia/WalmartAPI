package com.walmart.lab;

import com.walmart.lab.exceptions.WalmartException;
import com.walmart.lab.response.ProductRecommendationResponse;

import java.util.List;

/**
 * Created by Avirek Ghatia on 1/11/16.
 */
public class TestProductRecommendationRanker extends TestWalmartApiBase {

    ProductRecommendationRanker productRecommendationRanker;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        productRecommendationRanker = new ProductRecommendationRanker(apiKey);
    }

    public void test_happy_rankOrderModel() throws WalmartException {
        String query = "ipad";
        int limit = 10;
        List<ProductRecommendationResponse> rankedRecommendation = productRecommendationRanker.getRecommendations(query, limit);
        assertNotNull(rankedRecommendation);
        assertTrue(rankedRecommendation.size() >= limit);

        query = "ipad";
        limit = 0;
        rankedRecommendation = productRecommendationRanker.getRecommendations(query, limit);
        assertNotNull(rankedRecommendation);
        assertEquals(rankedRecommendation.size(), 0);

        query = "ipad";
        limit = -1;
        rankedRecommendation = productRecommendationRanker.getRecommendations(query, limit);
        assertNotNull(rankedRecommendation);
        assertEquals(rankedRecommendation.size(), 0);

        query = "ipad";
        limit = 20;
        rankedRecommendation = productRecommendationRanker.getRecommendations(query, limit);
        assertNotNull(rankedRecommendation);
        assertTrue(rankedRecommendation.size() >= limit);

        query = "clock";
        limit = 10;
        rankedRecommendation = productRecommendationRanker.getRecommendations(query, limit);
        assertNotNull(rankedRecommendation);
        assertTrue(rankedRecommendation.size() >= limit);

    }

    public void test_critical_rankOrderModel() throws WalmartException {
        List<ProductRecommendationResponse> rankedRecommendation;
        String query = "123456789";
        int limit = 10;

        rankedRecommendation = productRecommendationRanker.getRecommendations(query, limit);
        assertNotNull(rankedRecommendation);
        assertEquals(rankedRecommendation.size(), 0);

        query = "ipad      1234567";
        rankedRecommendation = productRecommendationRanker.getRecommendations(query, limit);
        assertNotNull(rankedRecommendation);
        assertEquals(rankedRecommendation.size(), 0);

        query = "ipad      ~!@#$%^&*";
        rankedRecommendation = productRecommendationRanker.getRecommendations(query, limit);
        assertNotNull(rankedRecommendation);
        assertEquals(rankedRecommendation.size(), 10);

        query = "~!@#$%^&*+_)(*&^%$#@~<>?:'''{}{}{";
        rankedRecommendation = productRecommendationRanker.getRecommendations(query, limit);
        assertNotNull(rankedRecommendation);
        assertEquals(rankedRecommendation.size(), 10);


    }

    public void test_walmart_exception() {

        String query = null;
        int limit = 10;

        try {
            productRecommendationRanker.getRecommendations(query, limit);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }


        query = "";
        try {
            productRecommendationRanker.getRecommendations(query, limit);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }


        query = "         ";
        try {
            productRecommendationRanker.getRecommendations(query, limit);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }


        query = "helloworld123";
        try {
            productRecommendationRanker.getRecommendations(query, limit);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "     198787 calulator";
        try {
            productRecommendationRanker.getRecommendations(query, limit);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }


        query = "0X723684ab";
        try {
            productRecommendationRanker.getRecommendations(query, limit);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

        query = "cycle&format=xml";
        try {
            productRecommendationRanker.getRecommendations(query, limit);
        } catch (Exception e) {
            assertTrue(e instanceof WalmartException);
        }

    }

    @Override
    protected void tearDown() throws Exception {
        productRecommendationRanker = null;
    }
}
