package com.example.bankservice.exception;

import com.example.bankservice.common.CommonException;
import com.example.bankservice.constant.ErrorCodes;

public class NotEnoughBalanceException extends CommonException {
    public NotEnoughBalanceException() {
        super("Balance is not enough for given user!\"", ErrorCodes.NOT_ENOUGH_BALANCE_EXCEPTION);
    }
}
