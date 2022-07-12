package com.example.productor.service;

import com.example.productor.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaSenderImpl implements KafkaSender{

  @Autowired
  KafkaTemplate kafkaTemplate;

  @Override
  public void sendMessage(String topico, Pedido pedido) {

    ListenableFuture<SendResult<String, Pedido>> future = kafkaTemplate.send(topico, pedido);
    future.addCallback(new ListenableFutureCallback<SendResult<String, Pedido>>() {

      @Override
      public void onFailure(Throwable ex) {
        ex.printStackTrace();
      }

      @Override
      public void onSuccess(SendResult<String, Pedido> result) {
        System.out.println("Enviado "+result.getProducerRecord().value().getProducto()
            +" al topic "+result.getRecordMetadata().topic());
      }
    });
  }

}
