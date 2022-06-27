package com.nttdata.bank.transaction.account.service;

import com.nttdata.bank.transaction.account.model.entity.document.TransactionAccount;
import com.nttdata.bank.transaction.account.model.entity.dto.TransactionAccountDto;
import com.nttdata.bank.transaction.account.model.repository.TransactionAccountRepository;
import com.nttdata.bank.transaction.account.model.service.TransactionAccountService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionAccountServiceImpl implements TransactionAccountService {

    @Autowired
    private TransactionAccountRepository transactionAccountRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public Flux<TransactionAccount> getAll() {
        return transactionAccountRepository.findAll();
    }

    @Override
    public Mono<TransactionAccount> save(Integer accountId, TransactionAccountDto transactionAccountDto) {
        TransactionAccount transactionAccountMono = mapper.map(transactionAccountDto,
                                                               TransactionAccount.class);
        return transactionAccountRepository.save(transactionAccountMono);
    }

    @Override
    public Mono<TransactionAccount> update(TransactionAccountDto transactionAccountDto) {
        TransactionAccount transactionAccountMono = mapper.map(transactionAccountDto,
                                                               TransactionAccount.class);
        return transactionAccountRepository.save(transactionAccountMono);
    }

    @Override
    public Mono<Void> delete(Integer transactionAccountId) {
        return transactionAccountRepository.deleteById(transactionAccountId);
    }

    @Override
    public Mono<TransactionAccount> findById(Integer transactionId) {
        return transactionAccountRepository.findById(transactionId);
    }


}
