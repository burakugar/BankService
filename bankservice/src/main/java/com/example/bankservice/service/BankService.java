package com.example.bankservice.service;

import com.example.bankservice.exception.NotEnoughBalanceException;
import com.example.bankservice.exception.UserNotFoundException;
import com.example.bankservice.model.converter.TransactionConverter;
import com.example.bankservice.model.converter.UserConverter;
import com.example.bankservice.model.dto.TransactionDto;
import com.example.bankservice.model.dto.UserDto;
import com.example.bankservice.model.entity.TransactionEntity;
import com.example.bankservice.model.entity.UserEntity;
import com.example.bankservice.model.request.DepositRequest;
import com.example.bankservice.model.request.TransactionAddRequest;
import com.example.bankservice.model.request.UserAddRequest;
import com.example.bankservice.model.request.WithdrawRequest;
import com.example.bankservice.repository.TransactionRepository;
import com.example.bankservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public UserDto getUserByName(String name) {
        return UserConverter
                .toUserDto(userRepository
                        .findByName(name)
                        .orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public UserDto addUser(UserAddRequest userAddRequest) {
        UserEntity userEntity = UserConverter.toUserEntity(userAddRequest);
        return UserConverter.toUserDto(userRepository.save(userEntity));
    }

    @Transactional
    public UserDto depositMoney(DepositRequest depositRequest) {
        String name = depositRequest.getName();
        Integer amount = depositRequest.getAmount();
        saveTheTransactionByDepositRequest(depositRequest);
        return userRepository.findByName(name).map(
                        user -> {
                            user.setBalance(user.getBalance() + amount);
                            return UserConverter.toUserDto(userRepository.save(user));
                        })
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public UserDto withdrawMoney(WithdrawRequest withdrawRequest) {
        String name = withdrawRequest.getName();
        Integer amount = withdrawRequest.getAmount();
        saveTheTransactionByWithdrawRequest(withdrawRequest);
        Optional<UserEntity> entity = userRepository.findByName(name).map(
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

    @Transactional
    public TransactionDto addTransaction(TransactionAddRequest transactionAddRequest) {
        TransactionEntity transactionEntity = TransactionConverter.toTransactionEntity(transactionAddRequest);
        decreaseSenderBalance(transactionAddRequest);
        increaseReceiventBalance(transactionAddRequest);
        return TransactionConverter
                .toTransactionDto(transactionRepository
                        .save(transactionEntity));
    }

    public List<TransactionDto> findAllTransactionsByNameAndType(String sender, String type) {
        return transactionRepository.findBySenderAndType(
                        sender,
                        type)
                .stream()
                .map(TransactionConverter::toTransactionDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findAllTransactionsByName(String sender) {
        return transactionRepository.findBySender(sender)
                .stream()
                .map(TransactionConverter::toTransactionDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findAllTransactionsByNameAndAmount(String name, Integer amount) {
        return transactionRepository.findBySenderAndAmount(name, amount)
                .stream()
                .map(TransactionConverter::toTransactionDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findAllTransactionsByDepositAmount(Integer amount) {
        return transactionRepository.findByAmount(amount)
                .stream()
                .map(TransactionConverter::toTransactionDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findAllTransactionsByNameAndTimestamp(String name, Date date) {
        Timestamp ts = new Timestamp(date.getTime());
        return transactionRepository.findBySenderAndTimestamp(name, ts)
                .stream()
                .map(TransactionConverter::toTransactionDto).collect(Collectors.toList());
    }

    public List<TransactionDto> findAllTransactionsByTimeStamp(Date date) {
        Timestamp ts = new Timestamp(date.getTime());
        return transactionRepository.findByTimestamp(ts)
                .stream()
                .map(TransactionConverter::toTransactionDto).collect(Collectors.toList());
    }

    @Transactional
    Optional<UserEntity> increaseReceiventBalance(TransactionAddRequest transactionAddRequest) {
        return userRepository.findByName(transactionAddRequest.getReceivent())
                .map(receiver -> {
                    receiver.setBalance(receiver.getBalance() + transactionAddRequest.getAmount());
                    userRepository.save(receiver);
                    return receiver;
                });
    }

    @Transactional
    Optional<UserEntity> decreaseSenderBalance(TransactionAddRequest transactionAddRequest) {
        return userRepository.findByName(transactionAddRequest.getSender())
                .map(sender -> {
                    Integer balance = sender.getBalance();
                    Integer transactionAmount = transactionAddRequest.getAmount();
                    if (balance - transactionAmount < 0) {
                        throw new NotEnoughBalanceException();
                    }
                    sender.setBalance(sender.getBalance() - transactionAmount);
                    return sender;
                });
    }

    @Transactional
    public void saveTheTransactionByDepositRequest(DepositRequest depositRequest) {
        UserEntity user = userRepository.findByName(depositRequest.getName()).get();
        TransactionEntity entity = TransactionEntity.builder()
                .type("deposit")
                .amount(depositRequest.getAmount())
                .sender(depositRequest.getName())
                .receivent(depositRequest.getName())
                .build();
        transactionRepository.save(entity);
    }

    @Transactional
    public void saveTheTransactionByWithdrawRequest(WithdrawRequest withdrawRequest) {
        UserEntity user = userRepository.findByName(withdrawRequest.getName()).get();
        TransactionEntity entity = TransactionEntity.builder()
                .type("withdraw")
                .amount(withdrawRequest.getAmount())
                .sender(withdrawRequest.getName())
                .receivent(withdrawRequest.getName())
                .build();
        transactionRepository.save(entity);
    }

}



