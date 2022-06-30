package com.nttdata.bank.account.model.entity.dto;

import lombok.Data;

@Data
public class ClientDto {
    private Integer clientId;
    private String clientName;
    private String clientType;
    private String clientDocument;

}