package com.nttdata.bank.transaction.account.model.repository;

import com.nttdata.bank.transaction.account.model.entity.document.TransactionAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionAccountRepository extends ReactiveMongoRepository<TransactionAccount,Integer> {
  Flux<TransactionAccount> getAllByNumberCard(String numberCard);
}
