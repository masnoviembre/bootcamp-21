package com.nttdata.bank.account.model.entity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String clientName;
    private char clientType;
    private String clientDocument;
}