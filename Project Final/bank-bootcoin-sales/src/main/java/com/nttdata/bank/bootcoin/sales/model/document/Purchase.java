package com.nttdata.bank.bootcoin.sales.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
  private Integer purchaseId;
  private Integer purchaseClientId;
  private Float amount;
  private String payMode;
}
