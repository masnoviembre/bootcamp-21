package com.nttdata.bank.bootcoin.sales.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sales")
public class Sales {

  @Id
  private Integer salesId;
  private Integer purchaseClientId;
  private Float amount;
  private String payMode;
  private Integer transactionNumber;
  private String accountNumber;
  private String cellPhoneNumber;
  private Integer purseId;

}
