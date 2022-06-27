package com.nttdata.bank.transaction.credit.service;


import com.nttdata.bank.transaction.credit.model.entity.document.TransactionCredit;
import com.nttdata.bank.transaction.credit.model.entity.dto.TransactionCreditDto;
import com.nttdata.bank.transaction.credit.model.repository.TransactionCreditRepository;
import com.nttdata.bank.transaction.credit.model.service.TransactionCreditService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionCreditServiceImpl implements TransactionCreditService {

    @Autowired
    private TransactionCreditRepository transactionCreditRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public Flux<TransactionCredit> getAll() {
        return transactionCreditRepository.findAll();
    }

    @Override
    public Mono<TransactionCredit> save(Integer creditId, TransactionCreditDto transactionCreditDto) {
        TransactionCredit transactionCreditMono = mapper.map(transactionCreditDto,
                                                             TransactionCredit.class);
        return transactionCreditRepository.save(transactionCreditMono);
    }

    @Override
    public Mono<TransactionCredit> update(TransactionCreditDto transactionCreditDto) {
        TransactionCredit transactionCreditMono = mapper.map(transactionCreditDto,
                                                               TransactionCredit.class);
        return transactionCreditRepository.save(transactionCreditMono);
    }

    @Override
    public Mono<Void> delete(Integer transactionCreditId) {
        return transactionCreditRepository.deleteById(transactionCreditId);
    }

    @Override
    public Mono<TransactionCredit> findById(Integer transactionId) {
        return transactionCreditRepository.findById(transactionId);
    }


}
