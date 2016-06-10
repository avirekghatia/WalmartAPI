package com.walmart.lab.api;

import com.ning.http.client.Response;
import com.walmart.lab.conf.ApiConstants;
import com.walmart.lab.exceptions.WalmartException;
import com.walmart.lab.response.ApiResponse;
import com.walmart.lab.response.ReviewResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * ReviewApi class, A concrete implementation for consuming Walmart Reviews API.<br>
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 * @see <a href="https://developer.walmartlabs.com/docs/read/Reviews_Api">Reviews Api</a>
 */
public class ReviewApi extends BaseApi {

    ReviewApi(String apiKey) {
        super();
        this.apiKey = apiKey;
        this.feedUrl += ApiConstants.REVIEWS_RESOURCE;
    }

    /**
     * Hits Review api and provide the review response in ApiResponse Facade.<br>
     *
     * @param queryString Item ID in String form(Should be integer)
     * @return apiResponse ApiResponse Facade which has ReviewResponse
     * @exception WalmartException, InterruptedException, ExecutionException, IOException
     * */
    @Override
    public ApiResponse getResponse(String queryString) throws InterruptedException, ExecutionException, IOException, WalmartException {
        ApiResponse apiResponse;
        if (!isEmpty(queryString)) {
            try {
                Integer itemId = Integer.parseInt(queryString);
                String serviceUrl = this.feedUrl + "/" + itemId;
                Future<Response> f = getResponseFuture(ApiConstants.REVIEWS, ""/*Empty Query String*/, serviceUrl);
                Response r = f.get();
                apiResponse = new ApiResponse();
                apiResponse.setResponse(objectMapper.readValue(r.getResponseBody(), ReviewResponse.class));
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
