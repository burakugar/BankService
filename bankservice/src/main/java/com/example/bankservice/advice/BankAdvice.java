package com.example.bankservice.advice;

import com.example.bankservice.common.BaseAdvice;
import com.example.bankservice.common.Error;
import com.example.bankservice.constant.ErrorCodes;
import com.example.bankservice.controller.BankController;
import com.example.bankservice.exception.NotEnoughBalanceException;
import com.example.bankservice.exception.TransactionNotFoundException;
import com.example.bankservice.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.bankservice.common.ErrorUtil.constructError;


@ControllerAdvice(assignableTypes = BankController.class)
@Slf4j
public class BankAdvice extends BaseAdvice {
    public BankAdvice() {
        super(ErrorCodes.UNKNOWN_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> handleException(UserNotFoundException e) {
        log.error("User not found with given id!", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(constructError(e));
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Error> handleException(TransactionNotFoundException e) {
        log.error("Transaction is not found!", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(constructError(e));
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public ResponseEntity<Error> handleException(NotEnoughBalanceException e) {
        log.error("The balance is not enough for operation!", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(constructError(e));
    }

}


