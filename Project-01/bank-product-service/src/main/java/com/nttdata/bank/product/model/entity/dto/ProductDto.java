package com.nttdata.bank.product.model.entity.dto;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;


@Data
public class ProductDto {

  @NotNull(message = "el código es requerido")
  private Integer productId;

  @NotNull(message = "el nombre es requerido")
  @Size(min = 2, max = 30, message = "El nombre debe tener entre {min} y {max} caracteres")
  private String productName;

  @NotNull
  @Pattern(regexp = "^[A-P]", message = "El tipo de Producto debe ser Pasivo ó Activo")
  private String productType;

  private String productSubType;

  @Min(value = 0, message = "Mantenimiento debe ser mayor o igual a cero")
  private Float productMaintenance;

  @Min(value = 0, message = "Movimientos mensuales deben ser mayores iguales a cero")
  private Integer productMonthlyMovements;
}