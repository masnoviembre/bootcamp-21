package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.entity.document.Client;
import com.nttdata.bank.client.model.entity.dto.AccountDto;
import com.nttdata.bank.client.model.entity.dto.ClientDto;
import com.nttdata.bank.client.model.entity.dto.CreditDto;
import com.nttdata.bank.client.model.repository.ClientRepository;
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
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> save(ClientDto clientDto) {
        Client clientMono = mapper.map(clientDto, Client.class);
        return clientRepository.save(clientMono);
    }

    @Override
    public Mono<Client> update(ClientDto clientDto) {
        Client clientMono = mapper.map(clientDto, Client.class);
        return this.clientRepository.save(clientMono);
    }

    @Override
    public Mono<Void> delete(Integer clientId) {
        return this.clientRepository.deleteById(clientId);
    }

    @Override
    public Mono<Client> findById(Integer clientId) {
        return this.clientRepository.findById(clientId);
    }

    @Override
    public Flux<AccountDto> getAccountByClientId(Integer clientId) {
        return externalService.getAccountByClientId(clientId);
    }

    @Override
    public Flux<CreditDto> getCreditByClientId(Integer clientId) {
        return externalService.getCreditByClientId(clientId);
    }

}
