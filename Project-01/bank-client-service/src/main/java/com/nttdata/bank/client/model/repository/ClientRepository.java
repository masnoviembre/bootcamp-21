package com.nttdata.bank.client.model.repository;

import com.nttdata.bank.client.model.document.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client,Integer> {
}
