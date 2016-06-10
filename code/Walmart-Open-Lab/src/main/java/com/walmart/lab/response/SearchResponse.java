package com.walmart.lab.response;

import java.io.Serializable;
import java.util.List;

/**
 * Response object of Search API
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 * @see <a href="https://developer.walmartlabs.com/docs/read/Search_API">Search API</a>
 * @see <a href="https://developer.walmartlabs.com/docs/read/Item_Field_Description">Search API</a>
 */
public class SearchResponse implements Serializable {

    // Query parameter
    private String query;

    // Category
    private String categoryId;

    // Sort Criteria
    private String sort;

    // Sort order
    private String order;

    // format
    private String format;

    // ApiResponse Group
    private String responseGroup;

    // Total number of items matching search criteria
    private int totalResults;

    // Start Index of items
    private int start;

    // Current Page number
    private int numItems;

    private List<Item> items;

    private String message;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getResponseGroup() {
        return responseGroup;
    }

    public void setResponseGroup(String responseGroup) {
        this.responseGroup = responseGroup;
    }

}
