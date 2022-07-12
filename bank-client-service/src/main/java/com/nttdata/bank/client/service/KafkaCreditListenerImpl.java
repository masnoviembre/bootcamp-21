package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.repository.entity.dto.CreditDto;
import com.nttdata.bank.client.model.service.KafkaCreditListener;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

//@Service
@Component
public class KafkaCreditListenerImpl implements KafkaCreditListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaCreditListenerImpl.class);

  List<CreditDto> creditDtos = new ArrayList<>();

  @KafkaListener(topics = "creditosTopic", groupId = "group")
  public void listenTopic(CreditDto creditDto) {
    LOGGER.info("Por aqui tambein pasa");
    System.out.println("Ha llegado crédito: " + creditDto.getCreditId());
    creditDtos.add(creditDto);
  }

  @Override
  public List<CreditDto> getCredits() {
    System.out.println("NO llama a Listener");
    return creditDtos;
  }
}
