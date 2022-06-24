package com.nttdata.bank.client.model.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String accountNumber;
    private char accountType;
    private Float accountMount;
    private Integer clientId;
}