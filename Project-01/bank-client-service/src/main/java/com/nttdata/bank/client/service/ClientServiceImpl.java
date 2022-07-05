package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.entity.document.Client;
import com.nttdata.bank.client.model.entity.dto.AccountDto;
import com.nttdata.bank.client.model.entity.dto.ClientDto;
import com.nttdata.bank.client.model.entity.dto.CreditDto;
import com.nttdata.bank.client.model.repository.ClientRepository;
import com.nttdata.bank.client.model.service.ClientService;
import java.util.Objects;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Null;

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
          }
          else {
            return Mono.empty();
          }
        }));
  }

  @Override
  public Mono<Client> update(ClientDto clientDto) {
    return clientRepository.findById(clientDto.getClientId())
        .map(c -> mapper.map(clientDto, Client.class))
        .flatMap(clientRepository::save)
        .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<Void> delete(Integer clientId) {
    Flux<AccountDto> accountDtoFlux = externalService.getAccountByClientId(clientId);
    accountDtoFlux.hasElements()
                  .map(isAvailable -> {
                      if (!isAvailable) {
                        Flux<CreditDto> creditDtoFlux = externalService.getCreditByClientId(clientId);
                        creditDtoFlux.hasElements().map(isHave->{
                          if (!isHave) {
                            clientRepository.deleteById(clientId);
                          } else {
                            Mono.empty();
                          }
                          return Mono.empty();
                        });
                      }
                    return null;
                  });
    return Mono.empty();
  }

  @Override
  public Mono<Client> findById(Integer clientId) {
    return clientRepository.findById(clientId);
  }

  @Override
  public Flux<?> getAllByClientId(Integer clientId){
    Flux<AccountDto> accountDtoFlux = externalService.getAccountByClientId(clientId);
    Flux<CreditDto> creditDtoFlux = externalService.getCreditByClientId(clientId);
    return Flux.mergeSequential(accountDtoFlux,creditDtoFlux);
  }

  public Flux<?> getAllMovements (String typeProduct, String numberProduct) {
      if (typeProduct.equalsIgnoreCase("A")) {
        AccountDto accountDto = externalService.getAccountByAccountNumber(numberProduct);
        if (accountDto != null ) {
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
