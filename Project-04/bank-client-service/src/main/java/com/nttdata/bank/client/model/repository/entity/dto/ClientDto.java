package com.nttdata.bank.client.model.repository.entity.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientDto {

  @NotNull(message = "el código es requerido")
  private Integer clientId;

  @NotNull(message = "el nombre es requerido")
  @Size(min = 2, max = 30, message = "El nombre debe tener entre {min} y {max} caracteres")
  private String clientName;

  @NotNull
  @Pattern(regexp = "^[A-P]", message = "El tipo de Cliente debe ser Personal ó Empresarial")
  private String clientType;

  @NotNull
  @Size(min = 8, max = 11, message = "El documento debe teher entre {min} y {max} numeros")
  private String clientDocument;

}