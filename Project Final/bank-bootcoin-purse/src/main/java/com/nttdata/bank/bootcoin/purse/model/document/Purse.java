package com.nttdata.bank.bootcoin.purse.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "purse")
public class Purse {

  @Id
  private Integer purseId;
  private Integer clientId;
  private String  documentType;
  private String  documentNumber;
  private String  email;
  private Float coinsAmount;
}
