package com.nttdata.bank.transaction.credit.model.entity.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "transactions_credits")
public class TransactionCredit {
    @Id
    private Integer transactiontId;
    private String transactionType; // abomo - cargo
    private Integer creditId;
    private String creditNumber;
    private Float transactionAmount;
    private Integer MonthPay;
    private String depositorDocument;
    private Date transactionDate;
}
