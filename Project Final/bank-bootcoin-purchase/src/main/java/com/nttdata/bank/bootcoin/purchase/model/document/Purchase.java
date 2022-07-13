package com.nttdata.bank.bootcoin.purchase.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "purchase")
public class Purchase {

  @Id
  private Integer purchaseId;
  private Float amount;
  private String payMode;

}
