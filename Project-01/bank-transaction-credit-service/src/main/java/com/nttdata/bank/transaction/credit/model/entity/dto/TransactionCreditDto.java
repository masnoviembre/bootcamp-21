package com.nttdata.bank.transaction.credit.model.entity.dto;

import lombok.Data;
import java.util.Date;

@Data
public class TransactionCreditDto {

    private Integer transactionId;
    private String transactionType; // abomo - cargo
    private Integer creditId;
    private String creditNumber;
    private Float transactionAmount;
    private Integer duesNumber;
    private String depositorDocument;
    private Date transactionDate;

}
