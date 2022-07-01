package com.nttdata.bank.account.model.entity.dto;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AccountDto {

    private Integer accountId;

    private String accountNumber;

    private Integer clientId;

    private Integer productId;

    private Float accountBalance;
}