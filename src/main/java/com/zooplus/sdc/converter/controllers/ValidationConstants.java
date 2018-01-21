package com.zooplus.sdc.converter.controllers;

public class ValidationConstants {

    public static final String CURRENCY_PATTERN = "^[a-zA-z]{3}$";
    public static final String USER_NAME_PATTERN = "^\\w$";

    public static final String BASE_CURRENCY_IS_REQUIRED = "Base currency is required.";
    public static final String TARGET_CURRENCY_IS_REQUIRED = "Target currency is required.";
    public static final String WRONG_CURRENCY_FORMAT = "Wrong currency format. Currency should be 3-letter code.";

    public static final String USER_NAME_IS_REQUIRED = "User name is required.";
    public static final String WRONG_USER_NAME_FORMAT = "User name can contains only letters and numbers.";

    private ValidationConstants() {
    }
}
