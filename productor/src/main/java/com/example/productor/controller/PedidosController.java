package com.example.productor.controller;

import com.example.productor.model.Pedido;
import com.example.productor.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class PedidosController {
  @Value("${topic}")
  String topic;

  @Autowired
  KafkaSender sender;

  @PostMapping(value = "pedido/{nombre}/{unidades}/{precio}")
  public void pedido (@PathVariable("nombre") String nombre,
                      @PathVariable("unidades") int unidades,
                      @PathVariable("precio") double precio) {
    Pedido pedido = new Pedido(nombre, unidades, precio);
    sender.sendMessage(topic,pedido);


  }

}

