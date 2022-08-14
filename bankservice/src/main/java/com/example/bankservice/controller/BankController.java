package com.example.bankservice.controller;

import com.example.bankservice.model.dto.TransactionDto;
import com.example.bankservice.model.dto.UserDto;
import com.example.bankservice.model.request.DepositRequest;
import com.example.bankservice.model.request.TransactionAddRequest;
import com.example.bankservice.model.request.UserAddRequest;
import com.example.bankservice.model.request.WithdrawRequest;
import com.example.bankservice.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping("/user")
    public UserDto addUser(@RequestBody UserAddRequest userAddRequest) {
        return bankService.addUser(userAddRequest);
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable Integer id) {
        return bankService.getUserById(id);
    }

    @PatchMapping("/deposit")
    public UserDto depositMoney(@RequestBody DepositRequest depositRequest) {
        return bankService.depositMoney(depositRequest);
    }

    @PatchMapping("/withdraw")
    public UserDto withDrawMoney(@RequestBody WithdrawRequest withdrawRequest) {
        return bankService.withdrawMoney(withdrawRequest);
    }

    @PostMapping("/transaction")
    public TransactionDto addTransaction(@RequestBody TransactionAddRequest transactionRequest) {
        return bankService.addTransaction(transactionRequest);
    }

    @RequestMapping(value = "/transaction/{sender}/{type}", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionHistoryWithSenderNameAndType(@PathVariable String sender, @PathVariable String type) {
        return bankService.findAllTransactionsByNameAndType(sender, type);
    }

    @RequestMapping(value = "/transaction/{sender}/{amount}", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionHistoryWithSenderNameAndAmount(@PathVariable String sender, @PathVariable Integer amount) {
        return bankService.findAllTransactionsByNameAndAmount(sender, amount);
    }

    @RequestMapping(value = "/transaction/{sender}/{date}", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionHistoryWithSenderNameAndDate(@PathVariable String sender, @PathVariable Date date) {
        return bankService.findAllTransactionsByNameAndTimestamp(sender, date);
    }

    @RequestMapping(value = "/transaction/{sender}", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionHistoryWithSenderName(@PathVariable String sender) {
        return bankService.findAllTransactionsByName(sender);
    }

    @RequestMapping(value = "/transaction/{amount}", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionHistoryWithDepositAmount(@PathVariable Integer amount) {
        return bankService.findAllTransactionsByDepositAmount(amount);
    }

    @RequestMapping(value = "/transaction/{date}", method = RequestMethod.GET)
    public List<TransactionDto> getTransactionHistoryWithDate(@PathVariable Date date) {
        return bankService.findAllTransactionsByTimeStamp(date);
    }


}
