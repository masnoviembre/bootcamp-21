package com.nttdata.bank.transaction.credit.model.entity.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreditDto {

    private Integer creditId;
    private String creditNumber;
    private Integer clientId;
    private Integer productId;
    private Float creditLine;
    private Integer creditMonths;
    private Float creditBalance;
    private Date creditDateOpen;
    private Date creditDateClose;
}
