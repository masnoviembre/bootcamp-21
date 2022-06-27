package com.nttdata.bank.client.model.entity.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ClientDto {

    @NotNull(message="el código es requerido")
    private Integer clientId;

    @NotNull(message="el nombre es requerido")
    @Size(min=2, max=30, message="El nombre debe tener entre {min} y {max} caracteres")
    private String clientName;

    @NotNull
    @Pattern(regexp = "^[A-P]{1}", message = "El tipo de Cliente debe ser Personal ó Empresarial")
    private String clientType;

    @NotNull
    @Size(min=8, max=11, message="El documento debe teher entre {min} y {max} numeros")
    @Pattern(regexp="[0-9]+", message="El documento sólo puede tener dígitos")
    private String clientDocument;

}