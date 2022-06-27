package com.nttdata.bank.transaction.credit.model.entity.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "transactions_credits")
public class TransactionCredit {
    @Id
    private Integer transactionCreditId;
    private String transactionCreditType;
    private Integer creditId;
    private Float transactionCreditAmount;
}
