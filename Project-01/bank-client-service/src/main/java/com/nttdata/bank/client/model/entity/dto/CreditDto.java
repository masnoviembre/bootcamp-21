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
  private Integer clientId;
  private Integer productId;
  private Float creditAmount;
  private Integer creditMonths;
  private Float creditBalance;
}