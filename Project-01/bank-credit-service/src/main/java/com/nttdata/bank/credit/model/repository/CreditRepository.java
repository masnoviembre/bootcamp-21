package com.nttdata.bank.credit.model.repository;

import com.nttdata.bank.credit.model.entity.document.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit,Integer> {

    Flux<Credit> findByClientId(Integer clientId);

}
