package com.nttdata.bank.client.model.service;

import com.nttdata.bank.client.model.repository.entity.dto.CreditDto;
import java.util.List;

public interface KafkaCreditListener {
  List<CreditDto> getCredits();
}
