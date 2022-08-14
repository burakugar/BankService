package com.example.bankservice.model.converter;

import com.example.bankservice.model.dto.TransactionDto;
import com.example.bankservice.model.entity.TransactionEntity;
import com.example.bankservice.model.request.TransactionAddRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public final class TransactionConverter {

    private TransactionConverter() {
        throw new IllegalStateException("Converter Class");
    }

    public static TransactionDto toTransactionDto(TransactionEntity transactionEntity) {
        return TransactionDto.builder()
                .id(transactionEntity.getId())
                .receivent(transactionEntity.getReceivent())
                .sender(transactionEntity.getSender())
                .timestamp(transactionEntity.getTimestamp())
                .amount(transactionEntity.getAmount())
                .type(transactionEntity.getType())
                .build();
    }

    public static TransactionEntity toTransactionEntity(TransactionAddRequest transactionAddRequest) {
        return TransactionEntity.builder()
                .amount(transactionAddRequest.getAmount())
                .receivent(transactionAddRequest.getReceivent())
                .sender(transactionAddRequest.getSender())
                .type(transactionAddRequest.getType())
                .build();
    }

}
