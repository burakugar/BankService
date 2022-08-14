package com.example.bankservice.exception;

import com.example.bankservice.common.CommonException;
import com.example.bankservice.constant.ErrorCodes;

public class NegativeDepositBalanceException extends CommonException {
    public NegativeDepositBalanceException() {
        super("Deposit is negative!\"", ErrorCodes.NEGATIVE_DEPOSIT_EXCEPTION);
    }
}
