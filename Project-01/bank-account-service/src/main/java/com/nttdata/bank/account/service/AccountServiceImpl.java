package com.nttdata.bank.account.service;

import com.nttdata.bank.account.model.entity.document.Account;
import com.nttdata.bank.account.model.entity.dto.*;
import com.nttdata.bank.account.model.repository.AccountRepository;
import com.nttdata.bank.account.model.service.AccountService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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


  @SuppressWarnings({"checkstyle:CommentsIndentation", "checkstyle:SeparatorWrap"})
  @Override
  public Mono<Account> save(AccountDto accountDto) {
    return accountRepository.existsById(accountDto.getAccountId())
        .flatMap(isExist -> {
            if(!isExist) {
              ClientDto qq = externalService.externalFindByClientId(accountDto.getClientId());
              if (qq.getClientType().equalsIgnoreCase("P")) {

                Flux<Account> accountFlux = this.accountRepository.
                                                findByClientId(accountDto.getClientId());


/*            accountFlux.doOnNext(System.out::println).subscribe();
             //System.out.print(accountFlux.subscribe().toString());
              accountFlux.count()
                      .flatMap(c-> {
                  if (c.intValue() == 0){System.out.print("Esta Vacio");
                } else { System.out.print("Esta Lleno");}
                return null;
              });*/

                accountFlux.hasElements()
                    .flatMap (l->{
                      System.out.print("OKOK");
                      if (!l) {
                        return accountRepository.save(mapper.map(accountDto, Account.class));
                      } else {
                        return Mono.empty();
                         }
                    });
              } else {
                      ProductDto r = externalService.externalFindByProductId(accountDto.getProductId());
                      if (r.getProductSubType().equalsIgnoreCase("CC") ){
                          return accountRepository.save(mapper.map(accountDto, Account.class));
                      } else {
                              return Mono.empty();
                      }
              }
            }
          return Mono.empty();
        });
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
    return accountRepository.findById(accountId)
        .flatMap(a->accountRepository.deleteById(a.getAccountId()))
            .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<Account> findById(Integer accountId) {
    return this.accountRepository.findById(accountId);
  }

  @Override
  public Flux<Account> findByClientId(Integer clientId) {
    return this.accountRepository.findAll()
        .filter(p->p.getClientId().equals(clientId))
        .switchIfEmpty(Mono.empty());
  }


  @Override
  public Mono<Account> findByAccountNumber(String accountNumber) {
    return this.accountRepository.findAll()
               .filter(t->t.getAccountNumber().equals(accountNumber)).elementAt(0);

  }

  @Override
  public Flux<Object> getBalanceByClientId(Integer clientId) {
    return this.accountRepository.findAll()
        .filter(p->p.getClientId().equals(clientId))
        .flatMap(x->{
          Map<String, String> map = new HashMap<>();
          map.put("accountNumber", x.getAccountNumber());
          map.put("accountBalance", String.valueOf(x.getAccountBalance()));
          return Mono.just(map);
        });


    }

  @Override
  public Flux<Account> getAccountByProductId(String productId, String dateIni, String dateEnd)
                                              throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date date1 = sdf.parse(dateIni);
    Date date2 = sdf.parse(dateEnd);
    return this.accountRepository.findAll()
        .filter(p->p.getProductId().equals(productId) &&
                   p.getAccountDateOpen().after(date1) &&
                   p.getAccountDateOpen().before(date2))
        .switchIfEmpty(Mono.empty());
  }

}

