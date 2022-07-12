package com.example.consumidor.service;

import com.example.consumidor.model.Pedido;

import java.util.List;

public interface KafkaPedidoListener {
  List<Pedido> getPedidos();
}
