package com.nttdata.bank.credit.model.entity.dto;

import lombok.Data;

@Data
public class ProductDto {

  private Integer productId;
  private String productName;
  private String productType;
  private String productSubType;
  private Float productMaintenance;
  private Integer productMonthlyMovements;
}