package com.nttdata.bank.transaction.account.model.entity.dto;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class TransactionAccountDto {

    @NotNull(message="el código es requerido")
    private Integer transactionAccountId;

    @NotNull
    @Size(min=1, max=1, message="El Tipo debe tener un solo caracter")
    @Pattern(regexp="[A-C]+", message="El documento sólo puede tener Abono o Cargo")
    private String transactionAccountType;

    @NotNull(message="el código de la cuenta es requerido")
    private Integer accountId;

    @Min(value=1, message="El monto de la transaction debe ser mayor igual a uno")
    private Float transactionAccountAmount;
}
