package com.nttdata.bank.client.model.document;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    private String creditNumber;
    private char creditType;
    private Float creditAmount;
    private Integer creditMonths;
    private Integer clientId;
}