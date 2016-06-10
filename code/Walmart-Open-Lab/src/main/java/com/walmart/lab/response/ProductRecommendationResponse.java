package com.walmart.lab.response;

import java.io.Serializable;
import java.util.List;

/**
 * Response object of Product Recommendation API
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 * @see <a href="https://developer.walmartlabs.com/docs/read/Product_Recommendation_API">Product Recommendation API</a>
 */
public class ProductRecommendationResponse implements Serializable {
    private static final long serialVersionUID = 1234567891L;
    private int itemId;
    private int parentItemId;
    private String name;
    private float msrp;
    private float salePrice;
    private String upc;
    private String categoryPath;
    private String shortDescription;
    private String longDescription;
    private String brandName;
    private String thumbnailImage;
    private String mediumImage;
    private String largeImage;
    private String productTrackingUrl;
    private boolean ninetySevenCentShipping;
    private int standardShipRate;
    private String color;
    private boolean marketplace;
    private boolean shipToStore;
    private boolean freeShipToStore;
    private String modelNumber;
    private String productUrl;
    private String customerRating;
    private int numReviews;
    private List<Integer> variants;
    private String customerRatingImage;
    private String categoryNode;
    private boolean bundle;
    private boolean clearance;
    private boolean preOrder;
    private String stock;
    private String addToCartUrl;
    private String affiliateAddToCartUrl;
    private boolean freeShippingOver50Dollars;
    private boolean availableOnline;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(int parentItemId) {
        this.parentItemId = parentItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMsrp() {
        return msrp;
    }

    public void setMsrp(float msrp) {
        this.msrp = msrp;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public void setMediumImage(String mediumImage) {
        this.mediumImage = mediumImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getProductTrackingUrl() {
        return productTrackingUrl;
    }

    public void setProductTrackingUrl(String productTrackingUrl) {
        this.productTrackingUrl = productTrackingUrl;
    }

    public boolean isNinetySevenCentShipping() {
        return ninetySevenCentShipping;
    }

    public void setNinetySevenCentShipping(boolean ninetySevenCentShipping) {
        this.ninetySevenCentShipping = ninetySevenCentShipping;
    }

    public int getStandardShipRate() {
        return standardShipRate;
    }

    public void setStandardShipRate(int standardShipRate) {
        this.standardShipRate = standardShipRate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isMarketplace() {
        return marketplace;
    }

    public void setMarketplace(boolean marketplace) {
        this.marketplace = marketplace;
    }

    public boolean isShipToStore() {
        return shipToStore;
    }

    public void setShipToStore(boolean shipToStore) {
        this.shipToStore = shipToStore;
    }

    public boolean isFreeShipToStore() {
        return freeShipToStore;
    }

    public void setFreeShipToStore(boolean freeShipToStore) {
        this.freeShipToStore = freeShipToStore;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(String customerRating) {
        this.customerRating = customerRating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public List<Integer> getVariants() {
        return variants;
    }

    public void setVariants(List<Integer> variants) {
        this.variants = variants;
    }

    public String getCustomerRatingImage() {
        return customerRatingImage;
    }

    public void setCustomerRatingImage(String customerRatingImage) {
        this.customerRatingImage = customerRatingImage;
    }

    public String getCategoryNode() {
        return categoryNode;
    }

    public void setCategoryNode(String categoryNode) {
        this.categoryNode = categoryNode;
    }

    public boolean isBundle() {
        return bundle;
    }

    public void setBundle(boolean bundle) {
        this.bundle = bundle;
    }

    public boolean isClearance() {
        return clearance;
    }

    public void setClearance(boolean clearance) {
        this.clearance = clearance;
    }

    public boolean isPreOrder() {
        return preOrder;
    }

    public void setPreOrder(boolean preOrder) {
        this.preOrder = preOrder;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getAddToCartUrl() {
        return addToCartUrl;
    }

    public void setAddToCartUrl(String addToCartUrl) {
        this.addToCartUrl = addToCartUrl;
    }

    public String getAffiliateAddToCartUrl() {
        return affiliateAddToCartUrl;
    }

    public void setAffiliateAddToCartUrl(String affiliateAddToCartUrl) {
        this.affiliateAddToCartUrl = affiliateAddToCartUrl;
    }

    public boolean isFreeShippingOver50Dollars() {
        return freeShippingOver50Dollars;
    }

    public void setFreeShippingOver50Dollars(boolean freeShippingOver50Dollars) {
        this.freeShippingOver50Dollars = freeShippingOver50Dollars;
    }

    public boolean isAvailableOnline() {
        return availableOnline;
    }

    public void setAvailableOnline(boolean availableOnline) {
        this.availableOnline = availableOnline;
    }
}
