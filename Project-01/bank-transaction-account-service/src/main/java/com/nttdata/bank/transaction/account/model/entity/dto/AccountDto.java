package com.nttdata.bank.transaction.account.model.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AccountDto {

  private Integer accountId;
  private String accountNumber;
  private Integer clientId;
  private Integer productId;
  private Float accountBalance;
  private Date accountDateOpen;
  private Date accountDateClose;
  private String accountCardNumber;
  private Integer accountDebitOrder;
  private List<String> accountHeadlines = new ArrayList<>();
  private List<String> accountSignatories = new ArrayList<>();

}