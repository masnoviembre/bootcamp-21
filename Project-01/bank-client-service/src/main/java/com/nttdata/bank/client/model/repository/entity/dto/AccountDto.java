package com.nttdata.bank.client.model.repository.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
  private Integer accountId;
  private String accountNumber;
  private Integer clientId;
  private Integer productId;
  private Float accountBalance;
}