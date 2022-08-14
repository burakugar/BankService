package com.example.bankservice.controller;

import com.example.bankservice.model.dto.UserDto;
import com.example.bankservice.model.request.DepositRequest;
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
import org.springframework.web.bind.annotation.RestController;

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
    public UserDto getUserById(@PathVariable Integer id){
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


}
