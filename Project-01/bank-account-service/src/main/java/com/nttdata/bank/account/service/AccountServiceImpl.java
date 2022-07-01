package com.nttdata.bank.account.service;

import com.nttdata.bank.account.model.entity.document.Account;
import com.nttdata.bank.account.model.entity.dto.*;
import com.nttdata.bank.account.model.repository.AccountRepository;
import com.nttdata.bank.account.model.service.AccountService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private ExternalService externalService;


    @Override
    public Flux<Account> getAll() {
        return accountRepository.findAll().switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Account> save(AccountDto accountDto) {

        //Obtiene el tipo de cliente (Personal o Empresarial)
        //String typeClient = mapper.map(externalFindByClientId(accountDto.getClientId()),
        //                                                      ClientDto.class).getClientType();

        // Obtiene el tipo de Cuenta (Ahorros, plazo fijo, corriente)
        //String typeProduct = mapper.map(externalFindByProductId(accountDto.getProductId()),
        //                                                      ProductDto.class).getProductSubType();

        // Obtiene cuentas de clientes
        //Flux<Account> accountFlux = accountRepository.findByClientId(accountDto.getClientId());

        //if ((typeClient.equals("P")  && Objects.isNull(accountFlux)) ||
        //        (typeClient.equals("E")  && typeProduct.equals("CC"))) {
            return accountRepository.save(mapper.map(accountDto, Account.class));
        //}

        //return Mono.empty();
    }

    @Override
    public Mono<Account> update(AccountDto accountDto) {
        return accountRepository.findById(accountDto.getAccountId())
                .map(c->mapper.map(accountDto, Account.class))
                .flatMap(accountRepository::save)
                .switchIfEmpty(Mono.empty());
     }

    @Override
    public Mono<Void> delete(Integer accountId) {
        return this.accountRepository.deleteById(accountId);
    }

    @Override
    public Mono<Account> findById(Integer accountId) {
        return this.accountRepository.findById(accountId);
    }

    @Override
    public Flux<Account> findByClientId(Integer clientId) {
        return this.accountRepository.findAll()
                .filter(p->p.getClientId()==clientId);
    }

    @Override
    public Mono<ClientDto> externalFindByClientId(Integer clientId) {
        return externalService.externalFindByClientId((clientId));
    }

    @Override
    public Mono<ProductDto> externalFindByProductId(Integer productId) {
        return externalService.externalFindByProductId((productId));
    }

}
