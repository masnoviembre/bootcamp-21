package com.nttdata.bank.account.model.repository;

import com.nttdata.bank.account.model.entity.document.Account;
import com.nttdata.bank.account.model.entity.dto.AccountDto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account,Integer> {

    Flux<Account> findByClientId(Integer clientId);

    Mono<Account> findByAccountNumber(String accountNumber);

    Flux<Object> getBalanceByClientId(Integer clientId);

    Flux<Account> getAccountByProductId (String productId, String dateIni, String dateEnd);

}
