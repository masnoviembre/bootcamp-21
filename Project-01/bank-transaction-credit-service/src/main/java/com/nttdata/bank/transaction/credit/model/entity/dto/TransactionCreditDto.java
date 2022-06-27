package com.nttdata.bank.transaction.credit.model.entity.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class TransactionCreditDto {

    @NotNull(message="el código es requerido")
    private Integer transactionCreditId;

    @NotNull
    @Size(min=1, max=1, message="El Tipo debe tener un solo caracter")
    @Pattern(regexp="[A-C]+", message="El documento sólo puede tener Abono o Cargo")
    private String transactionCreditType;

    @NotNull(message="el código de la cuenta es requerido")
    private Integer creditId;

    @Min(value=1, message="El monto de la transaction debe ser mayor igual a uno")
    private Float transactionCreditAmount;
}
