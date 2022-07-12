package com.nttdata.bank.client.model.repository;

import com.nttdata.bank.client.model.repository.entity.document.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, Integer> {
  Flux<?> getAllByClientId(Integer clientId);
  //Flux<?> getAllMovements (String typeProduct, String numberProduct);

}
