package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.entity.document.Client;
import com.nttdata.bank.client.model.entity.dto.ClientDto;
import com.nttdata.bank.client.model.repository.ClientRepository;
import com.nttdata.bank.client.model.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

class ClientServiceImplTest {

  @Mock
  private ClientRepository clientRepository;

  @InjectMocks
  private ClientServiceImpl clientServiceImpl;

  private Client client;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    client = new Client();
    client.setClientId(1);
    client.setClientName("Pablo Maldonado");
    client.setClientType("P");
    client.setClientDocument("12345678");

  }

  @Test
  void save() {
    when(clientRepository.save(any(Client.class))).thenReturn(client);
    assertNotNull(clientServiceImpl.save(new ClientDto()));
  }
}