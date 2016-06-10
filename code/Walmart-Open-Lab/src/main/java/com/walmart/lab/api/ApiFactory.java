package com.walmart.lab.api;

import com.walmart.lab.conf.ApiConstants;

/**
 * API Factory<br>
 * <p>Use <b>getInstance()</b> to get the instance of a API.</p><br>
 * <b>ApiName</b><br>
 * <code><pre>
 *     ApiConstants.SEARCH<br>
 *     ApiConstants.REVIEWS<br>
 *     ApiConstants.PRODUCT_RECOMMENDATION
 * </pre></code><br>
 *
 * Example Use : <br>
 *     <code><pre>
 *         //get SearchApi instance
 *         BaseApi baseApi = ApiFactory.getInstance(ApiConstants.SEARCH,apiKey);
 *     </pre></code>
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 * @see <a href="https://developer.walmartlabs.com/">Walmart Open Lab</a>
 */
public class ApiFactory {

    /**
     * Factory method getInstance(String, String)
     *
     * @param apiName API Name or Type
     * @param apiKey API key is mandatory. Register at <a href="https://developer.walmartlabs.com/">Walmart Open Lab</a>
     *
     * @return BaseApi API available with Walmart
     * */
    public static BaseApi getInstance(String apiName, String apiKey) {
        switch (apiName) {

            case ApiConstants.SEARCH:
                return new SearchApi(apiKey);

            case ApiConstants.REVIEWS:
                return new ReviewApi(apiKey);

            case ApiConstants.PRODUCT_RECOMMENDATION:
                return new ProductRecommendationApi(apiKey);

            default:
                return null;
        }
    }
}
