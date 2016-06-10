package com.walmart.lab.api;

import com.ning.http.client.Response;
import com.walmart.lab.conf.ApiConstants;
import com.walmart.lab.exceptions.WalmartException;
import com.walmart.lab.response.ApiResponse;
import com.walmart.lab.response.ProductRecommendationResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * ProductRecommendationApi class, A concrete implementation for consuming Walmart Product Recommendation API.<br>
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 * @see <a href="https://developer.walmartlabs.com/docs/read/Product_Recommendation_API">Product Recommendation API</a>
 */
public class ProductRecommendationApi extends BaseApi {

    ProductRecommendationApi(String apiKey) {
        super();
        this.apiKey = apiKey;
        this.feedUrl += ApiConstants.PRODUCT_RECOMMENDATION_RESOURCE;
    }


    /**
     * Hits Product Recommendation api and provide the Array of ProductRecommendationResponse in ApiResponse Facade.<br>
     *
     * @param queryString Item ID in String form(Should be integer)
     * @return apiResponse ApiResponse Facade which has ProductRecommendationResponse[]
     * @exception WalmartException, InterruptedException, ExecutionException, IOException
     * */
    @Override
    public ApiResponse getResponse(String queryString) throws InterruptedException, ExecutionException, IOException, WalmartException {
        ApiResponse apiResponse;
        if (!isEmpty(queryString)) {
            try {
                Integer itemId = Integer.parseInt(queryString);
                Future<Response> f = getResponseFuture(ApiConstants.PRODUCT_RECOMMENDATION, itemId + "", feedUrl);
                Response r = f.get();
                apiResponse = new ApiResponse();
                apiResponse.setResponse(objectMapper.readValue(r.getResponseBody(), ProductRecommendationResponse[].class));
            } catch (NumberFormatException nfe) {
                throw new WalmartException("Item Id not an integer value\n" + nfe.getMessage());
            } catch (Exception e) {
                throw new WalmartException(e.getMessage());
            }
        } else {
            throw new WalmartException("Item Id is Empty.");
        }
        return apiResponse;
    }
}
