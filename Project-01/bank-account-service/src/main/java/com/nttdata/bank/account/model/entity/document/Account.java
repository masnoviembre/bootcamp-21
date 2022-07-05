package com.nttdata.bank.account.model.entity.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class Account {

  @Id
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