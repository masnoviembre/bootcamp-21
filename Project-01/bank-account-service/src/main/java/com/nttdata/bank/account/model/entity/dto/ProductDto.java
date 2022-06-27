package com.nttdata.bank.account.model.entity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productName;
    private char productType;
    private Float productMaintenance;
    private Integer productMonthlyMovements;
}