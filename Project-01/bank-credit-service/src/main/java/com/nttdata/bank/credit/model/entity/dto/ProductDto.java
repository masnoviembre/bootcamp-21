package com.nttdata.bank.credit.model.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productName;
    private char productType;
    private Float productMaintenance;
    private Integer productMonthlyMovements;
}