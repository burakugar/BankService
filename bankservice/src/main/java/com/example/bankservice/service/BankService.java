package com.example.bankservice.service;

import com.example.bankservice.exception.NotEnoughBalanceException;
import com.example.bankservice.exception.UserNotFoundException;
import com.example.bankservice.model.converter.UserConverter;
import com.example.bankservice.model.dto.UserDto;
import com.example.bankservice.model.entity.UserEntity;
import com.example.bankservice.model.request.DepositRequest;
import com.example.bankservice.model.request.UserAddRequest;
import com.example.bankservice.model.request.WithdrawRequest;
import com.example.bankservice.repository.TransactionRepository;
import com.example.bankservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public UserDto getUserById(Integer id) {
        return UserConverter
                .toUserDto(userRepository
                        .findById(id)
                        .orElseThrow(UserNotFoundException::new));
    }

    public UserDto addUser(UserAddRequest userAddRequest) {
        UserEntity userEntity = UserConverter.toUserEntity(userAddRequest);
        return UserConverter.toUserDto(userRepository.save(userEntity));
    }

    public UserDto depositMoney(DepositRequest depositRequest) {
        Integer id = depositRequest.getId();
        Integer amount = depositRequest.getAmount();
        return userRepository.findById(id).map(
                        user -> {
                            user.setBalance(user.getBalance() + amount);
                            userRepository.save(user);
                            return UserConverter.toUserDto(user);
                        })
                .orElseThrow(UserNotFoundException::new);
    }

    public UserDto withdrawMoney(WithdrawRequest withdrawRequest) {
        Integer id = withdrawRequest.getId();
        Integer amount = withdrawRequest.getAmount();
        Optional<UserEntity> entity = userRepository.findById(id).map(
                user -> {
                    if (user.getBalance() - amount < 0) {
                        throw new NotEnoughBalanceException();
                    }
                    user.setBalance(user.getBalance() - amount);
                    userRepository.save(user);
                    return user;
                });

        return UserConverter.toUserDto(entity.get());
    }

}


