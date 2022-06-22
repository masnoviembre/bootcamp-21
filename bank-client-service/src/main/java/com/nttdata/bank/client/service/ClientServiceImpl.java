package com.nttdata.bank.client.service;

import com.nttdata.bank.client.model.document.Client;
import com.nttdata.bank.client.model.repository.ClientRepository;
import com.nttdata.bank.client.model.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public Flux<Client> findAll() {
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
    public Mono<Void> delete(Integer id) {
        return this.clientRepository.deleteById(id);
    }
}
