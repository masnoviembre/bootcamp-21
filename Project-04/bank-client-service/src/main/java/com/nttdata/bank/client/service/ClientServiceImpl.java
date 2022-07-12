package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.repository.ClientRepository;
import com.nttdata.bank.client.model.repository.entity.document.Client;
import com.nttdata.bank.client.model.repository.entity.dto.AccountDto;
import com.nttdata.bank.client.model.repository.entity.dto.ClientDto;
import com.nttdata.bank.client.model.repository.entity.dto.CreditDto;
import com.nttdata.bank.client.model.service.ClientService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private Mapper mapper;

  @Autowired
  private ExternalService externalService;


  @Override
  public Flux<Client> getAll() {
    return clientRepository.findAll().switchIfEmpty(Flux.empty());
  }

  @Override
  public Mono<Client> save(ClientDto clientDto) {
    return clientRepository.existsById(clientDto.getClientId())
        .flatMap((isExist -> {
          if (!isExist) {
            return clientRepository.save(mapper.map(clientDto, Client.class));
          } else {
            return Mono.empty();
          }
        }));
  }

  @Override
  public Mono<Client> update(ClientDto clientDto) {
    return clientRepository.existsById(clientDto.getClientId())
        .flatMap((isExist -> {
          if (isExist) {
            return clientRepository.save(mapper.map(clientDto, Client.class));
          } else {
            return Mono.empty();
          }
        }));
  }

  @Override
  public Mono<Void> delete(Integer clientId) {
    return clientRepository.findById(clientId)
        .flatMap(p -> clientRepository.deleteById(clientId))
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<Client> findById(Integer clientId) {
    return clientRepository.findById(clientId);
  }

  //

  @Override
  public Flux<Object> getAllByClientId(Integer clientId) {
    Flux<AccountDto> accountDtoFlux = externalService.getAccountByClientId(clientId);
    Flux<CreditDto> creditDtoFlux = externalService.getCreditByClientId(clientId);
    return Flux.mergeSequential(accountDtoFlux, creditDtoFlux);
  }


  public Flux<?> getAllMovements(String typeProduct, String numberProduct) {
    if (typeProduct.equalsIgnoreCase("A")) {
      AccountDto accountDto = externalService.getAccountByAccountNumber(numberProduct);
      if (accountDto != null) {
        return externalService.getAccountMovementById(accountDto.getAccountId());
      } else {
        CreditDto creditDto = externalService.getCreditByAccountNumber(numberProduct);
        if (creditDto != null) {
          return externalService.getCreditMovementById(creditDto.getCreditId());
        }
      }
    }
    return Flux.empty();
  }

}
