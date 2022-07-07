package com.nttdata.bank.client.model.entity.dto;

import java.util.Date;
import lombok.Data;

@Data
public class TransactionAccountDto {

  private Integer transactionAccountId;
  private String transactionAccountType; //Cargo - Abomo
  private String transactionAccountMethod; // Metodo Tarjeta, ventanilla
  private Integer accountId;
  private String accountNumber;
  private String accountNumberCard;
  private Float transactionAccountAmount;
  private String depositorDocument;
  private Date transactionDate;

}
