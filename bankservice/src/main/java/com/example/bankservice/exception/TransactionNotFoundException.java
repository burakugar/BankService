package com.example.bankservice.exception;

import com.example.bankservice.common.CommonException;
import com.example.bankservice.constant.ErrorCodes;

public class TransactionNotFoundException extends CommonException {
    public TransactionNotFoundException() {
        super("No transaction found!\"", ErrorCodes.TRANSACTION_NOT_FOUND_EXCEPTION);
    }
}
