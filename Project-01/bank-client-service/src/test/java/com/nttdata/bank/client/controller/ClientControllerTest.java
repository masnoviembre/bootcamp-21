package com.nttdata.bank.client.controller;

import com.nttdata.bank.client.model.repository.ClientRepository;
import com.nttdata.bank.client.model.entity.document.Client;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientControllerTest {
 @MockBean
  private ClientRepository clientRepository;

  @Autowired
  private WebTestClient testClient;

  private Client client ;

  @BeforeEach
  public void setUp() {

    client = new Client();
    client.setClientId(1);
    client.setClientName("Cliente de Prueba");
    client.setClientType("P");
    client.setClientDocument("12345678");

    BDDMockito.when(clientRepository.findAll())
        .thenReturn(Flux.just(client));

    BDDMockito.when(clientRepository.findById(ArgumentMatchers.anyInt()))
        .thenReturn(Mono.just(client));

  }

  @Test
  @DisplayName("listAll returns a flux of clients")
  public void listAll_ReturnFluxOfClients_WhenSuccessful() {
    testClient
        .get()
        .uri("/client")
        .exchange()
        .expectStatus().is4xxClientError()
        .expectBody();
  }

  @Test
  @DisplayName("listAll returns a flux of clients")
  public void listAll_Flavor2_ReturnFluxOfClient_WhenSuccessful() {
    testClient
        .get()
        .uri("/clients")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Client.class)
        .hasSize(1)
        .contains(client);
  }

  @Test
  @DisplayName("findById returns a Mono with client when it exists")
  public void findById_ReturnMonoClient_WhenSuccessful() {
    testClient
        .get()
        .uri("/clients/{id}",1)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Client.class)
        .isEqualTo(client);
  }

  @Test
  @DisplayName("findById returns Mono error when client does not exist")
  public void findById_ReturnMonoError_WhenEmptyMonoIsReturned() {
    BDDMockito.when(clientRepository.findById(ArgumentMatchers.anyInt()))
        .thenReturn(Mono.empty());

    testClient
        .get()
        .uri("/client/{id}",1)
        .exchange()
        .expectStatus().isNotFound()
        .expectBody()
        .jsonPath("$.status").isEqualTo(404);

  }

}