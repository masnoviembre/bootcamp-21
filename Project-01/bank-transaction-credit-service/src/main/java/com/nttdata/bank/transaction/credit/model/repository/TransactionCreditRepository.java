package com.nttdata.bank.transaction.credit.model.repository;

import com.nttdata.bank.transaction.credit.model.entity.document.TransactionCredit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionCreditRepository extends ReactiveMongoRepository<TransactionCredit,Integer> {
  Flux<TransactionCredit> getAllByNumberCard(String numberCard);
}
