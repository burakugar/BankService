package com.example.bankservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionAddRequest {
    private String receivent;
    private String sender;
    private Integer amount;
    private String type;
}
