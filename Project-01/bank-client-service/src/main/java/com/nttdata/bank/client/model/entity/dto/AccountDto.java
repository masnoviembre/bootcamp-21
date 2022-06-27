package com.nttdata.bank.client.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Integer accountId;
    private String accountNumber;
    private char accountType;
    private Float accountMount;
    private Integer clientId;
}