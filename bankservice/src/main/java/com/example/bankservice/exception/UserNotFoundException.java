package com.example.bankservice.exception;

import com.example.bankservice.common.CommonException;
import com.example.bankservice.constant.ErrorCodes;

public class UserNotFoundException extends CommonException {
    public UserNotFoundException() {
        super("No city found with given id!\"", ErrorCodes.USER_NOT_FOUND);
    }
}
