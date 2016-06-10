package com.walmart.lab;

import com.walmart.lab.api.BaseApi;
import junit.framework.TestCase;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Avirek Ghatia on 1/11/16.
 */
public class TestWalmartApiBase extends TestCase {

    protected String apiKey;
    protected BaseApi baseApi;
    protected List<String> searchList;
    protected List<String> itemIdList;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        FileInputStream input = new FileInputStream("configuration.properties");
        Properties p = new Properties();
        p.load(input);
        this.apiKey = p.getProperty("apikey");
        int searchListTotal = Integer.parseInt(p.getProperty("searchListTotal"));
        int itemListTotal = Integer.parseInt(p.getProperty("itemListTotal"));
        searchList = new ArrayList<>(10);
        itemIdList = new ArrayList<>(10);
        int i = 0;
        for (; i < searchListTotal; i++) {
            String propertyName = "searchProduct" + i;
            searchList.add(p.getProperty(propertyName));
        }

        for (i = 0; i < itemListTotal; i++) {
            String propertyName = "itemid" + i;
            itemIdList.add(p.getProperty(propertyName));
        }

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (null != baseApi) {
            baseApi.tearDownHttpClient();
            baseApi = null;
        }
    }

    public void test_fake() {
    }
}
