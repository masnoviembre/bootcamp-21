package com.example.consumidor.controller;

import com.example.consumidor.model.Pedido;
import com.example.consumidor.service.KafkaPedidoListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ConsumidorController {

  @Autowired
  KafkaPedidoListener listener;

  @GetMapping(value = "pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Pedido> pedidos() {
    return listener.getPedidos();
  }

}
