package com.nttdata.bank.transaction.account.model.entity.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "transactions_accounts")
public class TransactionAccount {
    @Id
    private Integer transactionAccountId;
    private String transactionAccountType;
    private Integer accountId;
    private Float transactionAccountAmount;
}
