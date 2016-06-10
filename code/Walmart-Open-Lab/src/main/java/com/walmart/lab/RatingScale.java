package com.walmart.lab;

/**
 * Rating Scale w.r.t number of stars
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 */
public enum RatingScale {
    FIVE_STAR(3),
    FOUR_STAR(2),
    THREE_STAR(1),
    TWO_STAR(-1),
    ONE_STAR(-1);

    private int ratingWeight;

    RatingScale(int ratingWeight) {
        this.ratingWeight = ratingWeight;
    }

    public int getRatingWeight() {
        return ratingWeight;
    }
}
