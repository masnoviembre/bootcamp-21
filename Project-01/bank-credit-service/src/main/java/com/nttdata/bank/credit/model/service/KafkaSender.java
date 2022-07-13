package com.nttdata.bank.credit.model.service;

import com.nttdata.bank.credit.model.entity.document.Credit;

public interface KafkaSender {
  void sendMessage(String topico, Credit credit);
}
