package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.repository.entity.document.Client;
import com.nttdata.bank.client.model.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
@ExtendWith(SpringExtension.class)
class ClientServiceImplTest {

  @InjectMocks
  private ClientServiceImpl clientService;

  @Mock
  private ClientRepository clientRepository;

  private final Client client = new Client();

  @BeforeEach
  public void setUp() {
    BDDMockito.when(clientRepository.findAll())
        .thenReturn(Flux.just(client));

    BDDMockito.when(clientRepository.findById(ArgumentMatchers.anyInt()))
        .thenReturn(Mono.just(client));

    BDDMockito.when(clientRepository.delete(ArgumentMatchers.any(Client.class)))
        .thenReturn(Mono.empty());

  }

  @Test
  @DisplayName("getAll returns a flux of clients")
  public void findAll_ReturnFluxOfClients_WhenSuccessful() {
    StepVerifier.create(clientService.getAll())
        .expectSubscription()
        .expectNext(client)
        .verifyComplete();
  }

 @Test
  @DisplayName("findById returns a Mono with client when it exists")
  public void findById_ReturnMonoClient_WhenSuccessful() {
    StepVerifier.create(clientService.findById(1))
        .expectSubscription()
        .expectNext(client)
        .verifyComplete();
  }

  @Test
  @DisplayName("delete removes the client when successful")
  public void delete_ReturnMonoError_WhenEmptyMonoIsReturned() {
    BDDMockito.when(clientRepository.findById(ArgumentMatchers.anyInt()))
        .thenReturn(Mono.empty());

    StepVerifier.create(clientService.delete(1))
        .expectSubscription()
        .verifyComplete();
  }

}
