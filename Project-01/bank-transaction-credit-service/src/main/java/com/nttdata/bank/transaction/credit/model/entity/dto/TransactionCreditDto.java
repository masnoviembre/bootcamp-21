package com.nttdata.bank.transaction.credit.model.entity.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TransactionCreditDto {

    private Integer transactionId;
    private String transactionType; // abomo - cargo
    private Integer creditId;
    private String creditNumber;
    private Float transactionAmount;
    private Integer MonthPay;
    private String depositorDocument;
    private Date transactionDate;

}
