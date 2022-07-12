package com.example.productor.service;

import com.example.productor.model.Pedido;

public interface KafkaSender {
  void sendMessage(String topico, Pedido pedido);
}
