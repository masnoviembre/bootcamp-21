package com.nttdata.bank.client.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
    private Integer creditId;
    private String creditNumber;
    private char creditType;
    private Float creditAmount;
    private Integer creditMonths;
    private Integer clientId;
}