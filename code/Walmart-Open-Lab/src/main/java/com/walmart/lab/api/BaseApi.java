package com.walmart.lab.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.walmart.lab.conf.ApiConstants;
import com.walmart.lab.exceptions.WalmartException;
import com.walmart.lab.response.ApiResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * A base abstract class which acts as contract for all other Walmart API.<br>
 * Always call tearDownHttpClient() method at the end to close http connection(AsyncHttpClient)
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 */
public abstract class BaseApi {

    protected AsyncHttpClient asyncHttpClient;
    protected String feedUrl;
    protected String apiKey;
    protected ObjectMapper objectMapper;

    BaseApi() {
        this.asyncHttpClient = new AsyncHttpClient();
        this.feedUrl = ApiConstants.API_ENDPOINT;
        this.objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    abstract public ApiResponse getResponse(String queryString) throws InterruptedException, ExecutionException, IOException, WalmartException;

    /**
     * Actual API calling method which calls to Walmart API
     *
     * @param apiType Required to form an api-specific query parameter list
     * @param queryString Query parameter value
     * @param serviceUrl service url of API
     * @return f Future<Response>
     * */
    protected Future<Response> getResponseFuture(String apiType, String queryString, String serviceUrl) throws IOException {

        Future<Response> f;

        AsyncHttpClient.BoundRequestBuilder requestBuilder = asyncHttpClient.prepareGet(serviceUrl)
                .addQueryParameter(ApiConstants.API_KEY, apiKey)
                .addQueryParameter(ApiConstants.FORMAT, ApiConstants.JSON_FORMAT);

        if (!queryString.isEmpty() && !apiType.equals(ApiConstants.PRODUCT_RECOMMENDATION))
            requestBuilder = requestBuilder.addQueryParameter(ApiConstants.QUERY, queryString);

        if (apiType.equals(ApiConstants.PRODUCT_RECOMMENDATION))
            requestBuilder = requestBuilder.addQueryParameter(ApiConstants.ITEM_ID, queryString);

        f = requestBuilder.execute();

        return f;
    }

    public void tearDownHttpClient() {
        if (!asyncHttpClient.isClosed())
            asyncHttpClient.close();
    }

    protected boolean isEmpty(String str) {
        if (null == str || str.trim().isEmpty())
            return true;
        return false;
    }
}
