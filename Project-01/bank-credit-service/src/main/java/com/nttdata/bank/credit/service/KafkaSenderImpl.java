package com.nttdata.bank.credit.service;

import com.nttdata.bank.credit.model.entity.document.Credit;
import com.nttdata.bank.credit.model.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaSenderImpl implements KafkaSender {
  @Autowired
  KafkaTemplate kafkaTemplate;

  @Override
  public void sendMessage(String topico, Credit credit) {
    ListenableFuture<SendResult<String, Credit>> future = kafkaTemplate.send(topico, credit);
    future.addCallback(new ListenableFutureCallback<SendResult<String, Credit>>() {

      @Override
      public void onFailure(Throwable ex) {
        ex.printStackTrace();
      }

      @Override
      public void onSuccess(SendResult<String, Credit> result) {
        System.out.println("Enviado "+result.getProducerRecord().value().getCreditId()
                                     +" al topic "+result.getRecordMetadata().topic());
      }
    });
  }
}
