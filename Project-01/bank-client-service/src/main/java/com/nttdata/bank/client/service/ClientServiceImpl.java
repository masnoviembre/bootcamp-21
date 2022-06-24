package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.document.Account;
import com.nttdata.bank.client.model.document.Client;
import com.nttdata.bank.client.model.document.Credit;
import com.nttdata.bank.client.model.repository.ClientRepository;
import com.nttdata.bank.client.model.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ExternalService externalService;

    @Override
    public Flux<Client> getAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Mono<Client> save(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public Mono<Client> update(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public Mono<Void> delete(Integer clientId) {
        return this.clientRepository.deleteById(clientId);
    }

    @Override
    public Mono<Client> findById(Integer clientId) {
        return this.clientRepository.findById(clientId);
    }


    public Mono<Account> saveExternalAccount(Integer clientId, Account account){
        return externalService.saveAccount(clientId, account);
    }

    public Mono<Credit> saveExternalCredit(Integer clientId, Credit credit){
        return externalService.saveCredit(clientId, credit);
    }
}
