package com.nttdata.bank.client.model.repository;

import com.nttdata.bank.client.model.entity.document.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, Integer> {

}
