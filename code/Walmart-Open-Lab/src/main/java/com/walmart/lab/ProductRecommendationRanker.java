package com.walmart.lab;

import com.walmart.lab.api.ApiFactory;
import com.walmart.lab.api.BaseApi;
import com.walmart.lab.conf.ApiConstants;
import com.walmart.lab.exceptions.WalmartException;
import com.walmart.lab.response.Item;
import com.walmart.lab.response.ProductRecommendationResponse;
import com.walmart.lab.response.ReviewResponse;
import com.walmart.lab.response.SearchResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * ProductRecommendationRanker: Provides ranked recommended product list on the basis of Review Sentiments.
 * Ranking score is based on award winning paper "Mining Millions of Reviews: A Technique to Rank Products Based on Importance of Reviews".<br>
 * The above paper is not fully implemented here for deriving the score but has helped closely in calculating the Review Sentiments and score it.<br>
 * The above paper is based on eCommerce platform like Amazon,eBay etc. which provides platform to different seller.
 * Customers when write reviews on Amazon, eBay also mentions their experience with seller and ramble about different other things which is not the cae in Walmart.
 * Product reviews on Walmart are based on Walmart service and the product quality. Therefore here we have not weighted each sentence of review.
 * Our methodology is based on three factors, <br>
 *     - <b>Polarity</b>  Calculate review sentiments for each review by using RatingScale(OverAll Rating) * (1 + upvotes - downvotes)<br>
 *     - <b>Review Age (Hrp)</b> New review are more polished and about recent version of product therefore review age has to be balanced.
 *     Calculated for each  review by Log(CurrentYear - ReviewPublishedYear) +1 <br>
 *     - <b>Helpful Votes (Trp)</b> Votes on review tells about the authenticity of the review,
 *     therefore we calculate the Helpful Votes as ratio of Helpful votes on total votes i.e upVote / (upVote + downVote)<br>
 * <b>Score Function</b> (For Each Review Polarity * Review Age * Helpful Votes ) /  [(Sum of all Review Age) * (Sum of all Helpful Votes)]
 *<br>
 *Call getRecommendations(Query,limit) to get start.
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 * @see <a href ="http://users.eecs.northwestern.edu/~choudhar/Publications/MiningMillionsofReviewsATechniqueToRankProductsBasedOnImportanceofReviews.pdf"></a> Mining Millions of Reviews Paper </a>
 */
public class ProductRecommendationRanker {

    private BaseApi searchApi;
    private BaseApi reviewApi;
    private BaseApi productRecoApi;
    private int limit;

    ProductRecommendationRanker(String apiKey) {
        searchApi = ApiFactory.getInstance(ApiConstants.SEARCH, apiKey);
        reviewApi = ApiFactory.getInstance(ApiConstants.REVIEWS, apiKey);
        productRecoApi = ApiFactory.getInstance(ApiConstants.PRODUCT_RECOMMENDATION, apiKey);
    }

    ProductRecommendationRanker(String apiKey, int limit) {
        this(apiKey);
        this.limit = limit;
    }

    /**
     * Usage: java -jar [<i>JAR_NAME</i>] -limit [<i>LIMIT(1-20)|optional</i>] -key [<i>APPLICATION_KEY|mandatory</i>] -query [<i>SEARCH_STRING|mandatory</i>]
     * Usage: java -cp [<i>JAR_NAME</i>] com.walmart.lab.ProductRecommendationRanker -limit [<i>LIMIT(1-20)|optional</i>] -key [<i>APPLICATION_KEY|mandatory</i>] -query [<i>SEARCH_STRING|mandatory</i>]
     *
     * @param args message
     */
    public static void main(String[] args) {
        ProductRecommendationRanker productRecommendationRanker = null;
        String param = null, key = null, limit = null;
        StringBuilder queryBuilder = new StringBuilder();
        int l = 10;
        try {
            for (String str : args) {
                switch (str) {

                    case "-limit":
                    case "-l":
                        param = "limit";
                        break;
                    case "-key":
                    case "-k":
                        param = "key";
                        break;

                    case "-query":
                    case "-q":
                        param = "query";
                        break;

                    default:
                        switch (param) {

                            case "limit":
                                if (limit == null)
                                    limit = str.trim();
                                else
                                    throw new Exception("Cannot use limit twice");
                                break;

                            case "key":
                                if (key == null)
                                    key = str;
                                else throw new Exception("Only single api key is required");
                                break;

                            case "query":
                                queryBuilder.append(str.trim() + " ");
                                break;
                            default:
                                throw new WalmartException("Not a proper Usage");

                        }
                }
            }
            if (limit != null) {
                try {
                    l = Integer.parseInt(limit);
                } catch (NumberFormatException nfe) {
                    throw new WalmartException("Number format Exception. Limit should be an Integer value in range 1 -20");
                }
            }

            if (l < 1 || l > 20) {
                throw new WalmartException("Limit should in range of 1 to 20\n" +
                        " Parameter : -limit <LIMIT>");
            } else if (key == null) {
                throw new WalmartException("Api key is mandatory. Register for your API key on the Walmart Labs Developer website: https://developer.walmartlabs.com/ \n" +
                        " Parameter : -key <APPLICATION KEY>");

            } else if (null == queryBuilder || queryBuilder.toString().equals("")) {
                throw new WalmartException("Search Query is mandatory. Cannot recommend products without any search string\n Parameter : -query <QUERY>");
            }

            productRecommendationRanker = new ProductRecommendationRanker(key, l);

            // Calculate rank-order for recommended products based on Review
            System.out.println("Work in Progress... ");

            List<ProductRecommendationResponse> sortedProductRecoList = productRecommendationRanker.getRecommendations(queryBuilder.toString().trim(), l);

            if (sortedProductRecoList.size() <= 0) {
                System.out.println("Sorry, No Results found");
                return;
            }

            System.out.println("-- Item ID -- | -- Name -- ");

            for (ProductRecommendationResponse prr : sortedProductRecoList) {
                System.out.println(prr.getItemId() + "   " + prr.getName());
            }




        } catch (WalmartException wExp) {
            System.out.println("Error : " + wExp.getMessage());

            System.out.println("Usage instruction : java -jar <JAR_NAME> -limit <LIMIT(1-20)|optional> -key <APPLICATION_KEY|mandatory> -query <SEARCH_STRING|mandatory>");
        } catch (Exception e) {
            String failMessage = (e.getMessage() == null) ? "Incorrect call" : e.getMessage();
            System.out.println("Fail Message : " + failMessage);
            System.out.println("Usage instruction : java -jar <JAR_NAME> -limit <LIMIT(1-20)|optional> -key <APPLICATION_KEY|mandatory> -query <SEARCH_STRING|mandatory>");
            System.out.println("Parameters");
            System.out.println(" -key / -k (mandatory) Application Key. Register at https://developer.walmartlabs.com/");
            System.out.println(" -query / -q (mandatory) Search Query. Type: String");
            System.out.println(" -limit / -l (optional)  Integer value. Range 1 -20");
            System.out.println("Example: java -jar walmart-lab.jar -limit 20 -key <APPLICATION_KEY> -query cycle ");
            System.out.println("Example: java -jar walmart-lab.jar -key <APPLICATION_KEY> -query ipad");


        } finally {
            // tearing down the api connections
            if (productRecommendationRanker != null)
                productRecommendationRanker.tearDownProgram();
        }
    }

    /**
     * Calls SearchApi
     * @param queryString search query
     * @return SearchResponse
     * */
    private SearchResponse searchProduct(String queryString) throws WalmartException {
        try {
            return searchApi.getResponse(queryString).getSearchResponse();
        } catch (InterruptedException | ExecutionException | IOException | NullPointerException e) {
            throw new WalmartException(e.getMessage());
        }
    }

    /**
     * Calls ProductRecommendationApi
     * @param itemId Item Id
     * @return ProductRecommendationResponse[]
     * */
    private ProductRecommendationResponse[] getProductRecommendations(String itemId) throws WalmartException {

        try {
            return productRecoApi.getResponse(itemId).getProductRecommendationResponses();
        } catch (InterruptedException | ExecutionException | IOException | NullPointerException e) {
            throw new WalmartException(e.getMessage());
        }
    }

    /**
     * Calls ReviewApi
     *
     * @param itemId Item Id
     * @return ReviewResponse
     * */
    private ReviewResponse getProductReview(String itemId) throws WalmartException {

        try {
            return reviewApi.getResponse(itemId).getReviewResponse();
        } catch (InterruptedException | ExecutionException | IOException | NullPointerException e) {
            throw new WalmartException(e.getMessage());
        }
    }

    /**
     * getRecommendations(String , int ) Exposed method to get ranked list of recommended products
     *
     * @param queryString search query
     * @param limit result limit( 1 - 20 )
     *
     * @return List<ProductRecommendationResponse>
     * @exception WalmartException
     * */
    public List<ProductRecommendationResponse> getRecommendations(String queryString, int limit) throws WalmartException {
        this.limit = limit;
        return getRecommendations(queryString);
    }

    private List<ProductRecommendationResponse> getRecommendations(String queryString) throws WalmartException {
        // 1. Search for the product
        SearchResponse searchResponse = searchProduct(queryString);
        // If search response is not null and their is no result in response
        if (null == searchResponse || ApiConstants.NO_RESULT_MESSAGE.equalsIgnoreCase(searchResponse.getMessage()))
            return null;

        // 2. Pop the first item
        Item item = searchResponse.getItems().get(0);

        // 3. Retrieve product recommendation for Default:10 or "limit" product
        ProductRecommendationResponse[] prrs = getProductRecommendations(item.getItemId() + "");

        // 4. Rank computation for each recommended products
        List<ProductRankHelper> rankHelperList = new ArrayList<>();
        for (int i = 0; i < limit && i < prrs.length; i++) {
            //5. Hit Review API and retrieve review response product by product
            // send each Product Review object for score computation
            ReviewResponse reviewResponse = getProductReview(prrs[i].getItemId() + "");
            double score = calculateReviewScore(reviewResponse.getReviews());
            ProductRankHelper rankHelper = new ProductRankHelper(score, prrs[i]);
            rankHelperList.add(rankHelper);
        }

        Collections.sort(rankHelperList);

        List<ProductRecommendationResponse> sortedProductRecoList = new ArrayList<>();
        for (ProductRankHelper rankHelper : rankHelperList) {
            sortedProductRecoList.add(rankHelper.prr);
        }

        return sortedProductRecoList;
    }

    /**
     * Calculate review score for all the reviews of the product.<br>
     * Calls cumulativeScore() to calculate the score.
     *
     * @param reviews List of reviews
     * @return double
     * */
    private double calculateReviewScore(List<ReviewResponse.Review> reviews) {
        List<Score> scores;
        if (!reviews.isEmpty()) {
            scores = new ArrayList<>();
            for (ReviewResponse.Review review : reviews) {
                Score score = new Score();
                score.setTrp(calculateTrp(review.getSubmissionTime()));
                score.setHrp(calculateHrp(review.getUpVotes(), review.getDownVotes()));
                score.setPolarity(calculatePolarity(review.getOverallRating().getRating(), review.getUpVotes(), review.getDownVotes()));
                scores.add(score);
            }
            return cumulativeScore(scores);
        }

        return Double.MIN_VALUE;

    }

    /**
     * Calculate and return cumulative score
     *
     * @param scores List of scores
     * @return double Rank score of all reviews of product
     * */
    private double cumulativeScore(List<Score> scores) {
        double trpTotal = 0d, hrpTotal = 0d, numerator = 0d, finalScore = 0d;
        for (Score sc : scores) {
            numerator += sc.getPolarity() * sc.getHrp() * sc.getTrp();
            hrpTotal += sc.getHrp();
            trpTotal += sc.getTrp();
        }

        finalScore = numerator / (hrpTotal * trpTotal);

        return finalScore;

    }

    /**
     * Calculate Helpful Votes ratio to total ratio
     *
     * @param upVotes
     * @param downVotes
     *
     * @return double upvotes/(upVotes + downVotes)
     * */
    private double calculateHrp(String upVotes, String downVotes) {
        Double uv = Double.parseDouble(upVotes);
        Double dv = Double.parseDouble(downVotes);
        uv = (uv == 0) ? 1 : uv;
        double totalVotes = ((uv + dv) == 0) ? 1d : (uv + dv);
        return uv / totalVotes;
    }

    /**
     *Calculate Review Age
     *
     * @param submissionTime
     * @return double Log(CurrentYear - ReviewPublishYear) +1
     * */
    private double calculateTrp(String submissionTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        try {

            Date reviewDate = formatter.parse(submissionTime);
            String rd = formatter.format(reviewDate);

            Date currentDate = new Date();
            String cd = formatter.format(currentDate);

            Double diff = Double.parseDouble(cd) - Double.parseDouble(rd);

            return Math.log(diff) + 1d;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //In case of error return 1
        return 1d;
    }

    /**
     *Calculate Review sentiment
     *
     * @param rating Review rating
     * @param upVotes Up Votes
     * @param downVotes Down Votes
     *
     * @return double rating * (1 + upVotes - downVotes )
     * */
    private double calculatePolarity(String rating, String upVotes, String downVotes) {
        int ratingWeight = getRatingWeight(rating);
        // Additional 1 is added in case if upvote == downvote then diff is zero.
        return ratingWeight * (1 + Integer.parseInt(upVotes) - Integer.parseInt(downVotes));
    }

    /**
     * Get Rating weight
     * @param rating
     * @return int
     * */
    private int getRatingWeight(String rating) {
        switch (rating) {
            case "1":
                return RatingScale.ONE_STAR.getRatingWeight();
            case "2":
                return RatingScale.TWO_STAR.getRatingWeight();
            case "3":
                return RatingScale.THREE_STAR.getRatingWeight();
            case "4":
                return RatingScale.FOUR_STAR.getRatingWeight();
            case "5":
                return RatingScale.FIVE_STAR.getRatingWeight();
            default:
                return 0;
        }
    }

    /**
     * At the end, call this tearDownProgram() method to close all connections
     * */
    private void tearDownProgram() {
        searchApi.tearDownHttpClient();
        reviewApi.tearDownHttpClient();
        productRecoApi.tearDownHttpClient();
    }

    /**
     * Class ProductRankHelper : Helper class helps to sort the product in descending order using score.
     * */
    private class ProductRankHelper implements Comparable {
        private double score;
        private ProductRecommendationResponse prr;

        ProductRankHelper(double score, ProductRecommendationResponse prr) {
            this.score = score;
            this.prr = prr;
        }

        @Override
        public int compareTo(Object o) {
            ProductRankHelper helper = (ProductRankHelper) o;
            return (helper.score < this.score) ? -1 : (helper.score > this.score) ? 1 : 0;
        }
    }
}
