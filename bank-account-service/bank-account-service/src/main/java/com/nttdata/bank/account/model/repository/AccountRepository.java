package com.nttdata.bank.account.model.repository;

import com.nttdata.bank.account.model.document.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepository extends ReactiveMongoRepository <Account,Integer>{

    Flux<Account> findByIdClient(Integer idClient);


}
