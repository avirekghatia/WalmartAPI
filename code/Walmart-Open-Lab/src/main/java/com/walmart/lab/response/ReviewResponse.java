package com.walmart.lab.response;

import java.io.Serializable;
import java.util.List;

/**
 * Response object of Review API
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 * @see <a href="https://developer.walmartlabs.com/docs/read/Reviews_Api">Reviews API</a>
 */
public class ReviewResponse implements Serializable {

    private static final long serialVersionUID = 1234567892L;

    private int itemId;
    private String name;
    private int salePrice;
    private String upc;
    private String categoryPath;
    private String brandName;
    private String productTrackingUrl;
    private String productUrl;
    private String categoryNode;
    private List<Review> reviews;
    private ReviewStatistics reviewStatistics;
    private boolean availableOnline;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductTrackingUrl() {
        return productTrackingUrl;
    }

    public void setProductTrackingUrl(String productTrackingUrl) {
        this.productTrackingUrl = productTrackingUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getCategoryNode() {
        return categoryNode;
    }

    public void setCategoryNode(String categoryNode) {
        this.categoryNode = categoryNode;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public ReviewStatistics getReviewStatistics() {
        return reviewStatistics;
    }

    public void setReviewStatistics(ReviewStatistics reviewStatistics) {
        this.reviewStatistics = reviewStatistics;
    }

    public boolean isAvailableOnline() {
        return availableOnline;
    }

    public void setAvailableOnline(boolean availableOnline) {
        this.availableOnline = availableOnline;
    }

    public static class Review {
        private String name;
        private Rating overallRating;
        private List<Rating> productAttributes;
        private String reviewer;
        private String reviewText;
        private String submissionTime;
        private String title;
        private String upVotes;
        private String downVotes;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Rating getOverallRating() {
            return overallRating;
        }

        public void setOverallRating(Rating overallRating) {
            this.overallRating = overallRating;
        }

        public List<Rating> getProductAttributes() {
            return productAttributes;
        }

        public void setProductAttributes(List<Rating> productAttributes) {
            this.productAttributes = productAttributes;
        }

        public String getReviewer() {
            return reviewer;
        }

        public void setReviewer(String reviewer) {
            this.reviewer = reviewer;
        }

        public String getReviewText() {
            return reviewText;
        }

        public void setReviewText(String reviewText) {
            this.reviewText = reviewText;
        }

        public String getSubmissionTime() {
            return submissionTime;
        }

        public void setSubmissionTime(String submissionTime) {
            this.submissionTime = submissionTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpVotes() {
            return upVotes;
        }

        public void setUpVotes(String upVotes) {
            this.upVotes = upVotes;
        }

        public String getDownVotes() {
            return downVotes;
        }

        public void setDownVotes(String downVotes) {
            this.downVotes = downVotes;
        }

        public static class Rating {
            private String label;
            private String rating;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }
        }
    }

    public static class ReviewStatistics {
        private String averageOverallRating;
        private String overallRatingRange;
        private List<RatingDistribution> ratingDistributions;
        private String totalReviewCount;

        public String getAverageOverallRating() {
            return averageOverallRating;
        }

        public void setAverageOverallRating(String averageOverallRating) {
            this.averageOverallRating = averageOverallRating;
        }

        public String getOverallRatingRange() {
            return overallRatingRange;
        }

        public void setOverallRatingRange(String overallRatingRange) {
            this.overallRatingRange = overallRatingRange;
        }

        public List<RatingDistribution> getRatingDistributions() {
            return ratingDistributions;
        }

        public void setRatingDistributions(List<RatingDistribution> ratingDistributions) {
            this.ratingDistributions = ratingDistributions;
        }

        public String getTotalReviewCount() {
            return totalReviewCount;
        }

        public void setTotalReviewCount(String totalReviewCount) {
            this.totalReviewCount = totalReviewCount;
        }

        public static class RatingDistribution {
            private String count;
            private String ratingValue;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getRatingValue() {
                return ratingValue;
            }

            public void setRatingValue(String ratingValue) {
                this.ratingValue = ratingValue;
            }
        }
    }
}
