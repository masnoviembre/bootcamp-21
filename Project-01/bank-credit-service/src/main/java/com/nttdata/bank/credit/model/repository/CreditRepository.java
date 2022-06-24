package com.nttdata.bank.credit.model.repository;

import com.nttdata.bank.credit.model.document.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit,Integer> {
}
