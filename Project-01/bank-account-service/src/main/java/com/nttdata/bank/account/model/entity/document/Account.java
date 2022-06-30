package com.nttdata.bank.account.model.entity.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}