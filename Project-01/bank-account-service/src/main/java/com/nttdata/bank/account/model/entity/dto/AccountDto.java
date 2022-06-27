package com.nttdata.bank.account.model.entity.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AccountDto {

    @NotNull(message="el código es requerido")
    private Integer accountId;

    @NotNull
    @Size(min=10, max=10, message="El documento debe teher entre {min} y {max} numeros")
    @Pattern(regexp="[0-9]+", message="El documento sólo puede tener dígitos")
    private String accountNumber;

    @NotNull(message="el código de cliente es requerido")
    private Integer clientId;

    @NotNull(message="el código de producto es requerido")
    private Integer productId;

    @Min(value=0, message="saldo debe ser mayor igual a cero")
    private Float accountBalance;
}