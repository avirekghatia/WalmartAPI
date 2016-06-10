package com.walmart.lab.response;

/**
 * API Response Facade<br>
 * Class composites of all the response objects and acts as envelope for response.<br>
 * Makes it easy for developer to look up the API response<br>
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 */
public class ApiResponse {
    private SearchResponse searchResponse;
    private ReviewResponse reviewResponse;
    private ProductRecommendationResponse[] productRecommendationResponses;


    /**
     * Sets API response
     *
     * @param responseObject Object is assigned to right response
     * */
    public void setResponse(Object responseObject) {

        if (responseObject instanceof SearchResponse)
            searchResponse = (SearchResponse) responseObject;

        else if (responseObject instanceof ReviewResponse)
            reviewResponse = (ReviewResponse) responseObject;

        else if (responseObject instanceof ProductRecommendationResponse[])
            productRecommendationResponses = (ProductRecommendationResponse[]) responseObject;

    }

    public SearchResponse getSearchResponse() {
        return searchResponse;
    }

    public ReviewResponse getReviewResponse() {
        return reviewResponse;
    }

    public ProductRecommendationResponse[] getProductRecommendationResponses() {
        return productRecommendationResponses;
    }
}
