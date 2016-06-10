package com.walmart.lab.api;

import com.ning.http.client.Response;
import com.walmart.lab.conf.ApiConstants;
import com.walmart.lab.exceptions.WalmartException;
import com.walmart.lab.response.ApiResponse;
import com.walmart.lab.response.SearchResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * SearchApi class, A concrete implementation for consuming Walmart Search API.<br>
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 * @see <a href="https://developer.walmartlabs.com/docs/read/Search_API">Search Api</a>
 */
public class SearchApi extends BaseApi {

    SearchApi(String apiKey) {
        super();
        this.apiKey = apiKey;
        this.feedUrl += ApiConstants.SEARCH_RESOURCE;
    }

    /**
     * Hits search api and provide the search response in ApiResponse Facade.<br>
     *
     * @param queryString Search Query
     * @return apiResponse ApiResponse Facade which has SearchResponse
     * @exception WalmartException, InterruptedException, ExecutionException, IOException
     * */
    @Override
    public ApiResponse getResponse(String queryString) throws InterruptedException, ExecutionException, IOException, WalmartException {
        ApiResponse apiResponse;
        if (!isEmpty(queryString)) {
            try {
                Future<Response> f = getResponseFuture(ApiConstants.SEARCH, queryString, feedUrl);
                Response r = f.get();
                apiResponse = new ApiResponse();
                SearchResponse searchResponse = objectMapper.readValue(r.getResponseBody(), SearchResponse.class);
                if (ApiConstants.NO_RESULT_MESSAGE.equals(searchResponse.getMessage()))
                    throw new WalmartException("No Result Found!! Check your query String");

                apiResponse.setResponse(searchResponse);
            } catch (InterruptedException | ExecutionException | IOException e) {
                throw new WalmartException(e.getMessage());
            }
        } else {
            throw new WalmartException("Search query string is Empty.");
        }
        return apiResponse;

    }

}
