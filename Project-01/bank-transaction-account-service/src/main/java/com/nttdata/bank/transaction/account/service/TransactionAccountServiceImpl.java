package com.nttdata.bank.transaction.account.service;

import com.nttdata.bank.transaction.account.model.entity.document.Account;
import com.nttdata.bank.transaction.account.model.entity.document.TransactionAccount;
import com.nttdata.bank.transaction.account.model.entity.dto.AccountDto;
import com.nttdata.bank.transaction.account.model.entity.dto.TransactionAccountDto;
import com.nttdata.bank.transaction.account.model.repository.TransactionAccountRepository;
import com.nttdata.bank.transaction.account.model.service.TransactionAccountService;
import org.dozer.Mapper;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private ExternalService externalService;

    @Override
    public Flux<TransactionAccount> getAll() {
        return transactionAccountRepository.findAll();
    }

    @Override
    public Mono<TransactionAccount> save(TransactionAccountDto transactionAccountDto) {

      return transactionAccountRepository.existsById(transactionAccountDto.getTransactionId())
          .flatMap((isExist -> {
            if (!isExist) {

              Float amount = transactionAccountDto.getTransactionAmount();
              if (transactionAccountDto.getTransactionType().equalsIgnoreCase("C")) {
                amount = amount * -1;
              }

              AccountDto accountDtoMono = externalService.
                                          externalUpdateBalance (transactionAccountDto.getAccountId(),
                                                                  amount );

              return transactionAccountRepository.save(mapper.map(transactionAccountDto,
                                                                  TransactionAccount.class));
            } else {
              return Mono.empty();
            }
          }));

    }

    public Mono<TransactionAccount> saveByCardNumber(TransactionAccountDto transactionAccountDto) {
      Flux<AccountDto> accountDtoFlux = externalService.externalFindAccountByCardNumber(
                                                      transactionAccountDto.getCardNumber());

      accountDtoFlux.doOnNext( p -> {
                   if ( p.getAccountBalance() >= transactionAccountDto.getTransactionAmount()) {
                        transactionAccountDto.setAccountNumber(p.getAccountNumber());
                       TransactionAccount transactionAccountMono = mapper.map(transactionAccountDto,
                                                                  TransactionAccount.class);



                       Float amount = transactionAccountDto.getTransactionAmount();
                       if (transactionAccountDto.getTransactionType().equalsIgnoreCase("C")) {
                            amount = amount * -1;
                        }

                         Mono<AccountDto> accountDto = externalService.externalFindAccountById (
                                                         transactionAccountDto.getAccountNumber());
                        //accountDto.setAccountBalance(accountDto.getAccountBalance() + amount);
                        //externalService.externalUpdateBalance(accountDto);
                   }
                }).subscribe();

      return Mono.empty();
    }

    @Override
    public Mono<TransactionAccount> update(TransactionAccountDto transactionAccountDto) {
        return transactionAccountRepository.existsById(transactionAccountDto.getAccountId())
            .flatMap(isExist -> {
                if (isExist) {
                    transactionAccountRepository.deleteById(transactionAccountDto.getTransactionId());
                    return this.save(transactionAccountDto);
                }
                return Mono.empty();
            });
    }


    @Override
    public Mono<Void> delete(Integer transactionAccountId) {
        return transactionAccountRepository.findById(transactionAccountId)
            .map(p->{
                Float amount = p.getTransactionAmount();
                if (p.getTransactionType().equalsIgnoreCase("A")){
                    amount = amount * -1;
                }
                AccountDto accountDtoMono = externalService.
                                            externalUpdateBalance (p.getAccountId(),amount );
                return transactionAccountRepository.deleteById(p.getTransactionId());
            })
            .flatMap(a->transactionAccountRepository.deleteById(transactionAccountId)
                .switchIfEmpty(Mono.empty()));
    }

    @Override
    public Mono<TransactionAccount> findById(Integer transactionId) {
        return transactionAccountRepository.findById(transactionId);
    }

    @Override
    public Flux<TransactionAccount> getAllByCardNumber(String cardNumber) {
      return transactionAccountRepository.findAll()
                                        .filter(p->p.getAccountNumber() == cardNumber);
  }

}
