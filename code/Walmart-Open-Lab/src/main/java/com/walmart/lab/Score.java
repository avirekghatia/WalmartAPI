package com.walmart.lab;

/**
 * Placeholder for review score.
 *
 * @param polarity Weight of Review Sentiment
 * @param hrp Helpful votes ratio to total votes for a review
 * @param trp Review Age
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 * @see <a href ="http://users.eecs.northwestern.edu/~choudhar/Publications/MiningMillionsofReviewsATechniqueToRankProductsBasedOnImportanceofReviews.pdf"></a> Mining Millions of Reviews Paper </a>
 */
public class Score {

    private double polarity;
    // Helpful votes ratio of a review
    private double hrp;
    // Review age
    private double trp;

    public double getPolarity() {
        return polarity;
    }

    public void setPolarity(double polarity) {
        this.polarity = polarity;
    }

    public double getHrp() {
        return hrp;
    }

    public void setHrp(double hrp) {
        this.hrp = hrp;
    }

    public double getTrp() {
        return trp;
    }

    public void setTrp(double trp) {
        this.trp = trp;
    }

}
