package com.zooplus.sdc.converter.controllers;

public class ValidationConstants {

    public static final String CURRENCY_PATTERN = "^[a-zA-z]{3}$";

    public static final String BASE_CURRENCY_IS_REQUIRED = "Base currency is required.";
    public static final String TARGET_CURRENCY_IS_REQUIRED = "Target currency is required.";
    public static final String WRONG_CURRENCY_FORMAT = "Wrong currency format. Currency should be 3-letter code.";

    private ValidationConstants() {
    }
}
