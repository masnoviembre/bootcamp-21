package com.nttdata.bank.bootcoin.purse.model.repository;

import com.nttdata.bank.bootcoin.purse.model.document.Purse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PurseRepository extends ReactiveMongoRepository<Purse, Integer> {
}
