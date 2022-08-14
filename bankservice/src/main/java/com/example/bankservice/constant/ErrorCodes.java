package com.example.bankservice.constant;

public final class ErrorCodes {
    private ErrorCodes() {
        throw new IllegalStateException("Constant Class");
    }

    public static final Integer USER_NOT_FOUND = 19997;
    public static final Integer NOT_ENOUGH_BALANCE_EXCEPTION = 19998;
    public static final Integer UNKNOWN_ERROR = 19999;
}
