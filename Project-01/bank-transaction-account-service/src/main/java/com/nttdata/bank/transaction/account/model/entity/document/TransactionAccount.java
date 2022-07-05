package com.nttdata.bank.transaction.account.model.entity.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "transactions_accounts")
public class TransactionAccount {
    @Id
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
