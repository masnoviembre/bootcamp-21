package com.example.productor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pedido {
  private String producto;
  private int unidades;
  private double precio;

}
