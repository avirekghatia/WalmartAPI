package com.walmart.lab.exceptions;

/**
 * Custom Exception class for this API
 *
 * @author Avirek Ghatia - ag4682 at nyu.edu
 */
public class WalmartException extends Exception {
    public WalmartException(String errorMessage) {
        super(errorMessage);
    }
}
