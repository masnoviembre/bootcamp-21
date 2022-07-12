package com.example.consumidor.service;

import com.example.consumidor.model.Pedido;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaPedidoListenerImpl implements KafkaPedidoListener{
  List<Pedido> pedidos = new ArrayList<>();

 @KafkaListener(topics = "pedidosTopic", groupId = "group")
  public void listenTopic(Pedido pedido) {
    System.out.println("Ha llegado: "+pedido.getProducto());
    pedidos.add(pedido);
  }

  @Override
  public List<Pedido> getPedidos() {
    return pedidos;
  }
}
