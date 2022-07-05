package com.nttdata.bank.transaction.account.model.entity.dto;

import lombok.Data;
import javax.validation.constraints.*;
import java.util.Date;

@Data
public class TransactionAccountDto {

    private Integer transactionId;
    private String transactionType; //Cargo - Abomo
    private String transactionMethod; // Metodo Tarjeta, ventanilla
    private Integer accountId;
    private String accountNumber;
    private String cardNumber;
    private Float transactionAmount;
    private String depositorDocument;
    private Date transactionDate;

}
