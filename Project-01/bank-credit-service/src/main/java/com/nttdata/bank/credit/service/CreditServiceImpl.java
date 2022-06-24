package com.nttdata.bank.credit.service;

import com.nttdata.bank.credit.model.document.Credit;
import com.nttdata.bank.credit.model.repository.CreditRepository;
import com.nttdata.bank.credit.model.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Flux<Credit> getAll() {
        return this.creditRepository.findAll();
    }

    @Override
    public Mono<Credit> save(Credit credit) {
        return this.creditRepository.save(credit);
    }

    @Override
    public Mono<Credit> update(Credit credit) {
        return this.creditRepository.save(credit);
    }

    @Override
    public Mono<Void> delete(Integer creditId) {
        return this.creditRepository.deleteById(creditId);
    }

    @Override
    public Mono<Credit> findById(Integer creditId) {
        return this.creditRepository.findById(creditId);
    }
}
