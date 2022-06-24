package com.nttdata.bank.credit.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credits")
public class Credit {

    @Id
    private Integer creditId;
    private String creditNumber;
    private char creditType;
    private Float creditAmount;
    private Integer creditMonths;
}