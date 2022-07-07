package com.nttdata.bank.account.model.entity.dto;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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