package com.nttdata.bank.transaction.credit.service;

import com.nttdata.bank.transaction.credit.model.entity.document.TransactionCredit;
import com.nttdata.bank.transaction.credit.model.entity.dto.CreditDto;
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

    @Autowired
    private ExternalService externalService;

    @Override
    public Flux<TransactionCredit> getAll() {
        return transactionCreditRepository.findAll();
    }

    @Override
    public Mono<TransactionCredit> save(TransactionCreditDto transactionCreditDto) {

        Float amount = transactionCreditDto.getTransactionAmount();
        if (transactionCreditDto.getTransactionType().equalsIgnoreCase("C")) {
            amount = amount * -1;
        }

        return transactionCreditRepository.existsById(transactionCreditDto.getTransactionId())
            .flatMap((isExist -> {
                if (!isExist) {
                    return transactionCreditRepository.save(mapper.map(transactionCreditDto,
                        TransactionCredit.class));
                } else {
                    return Mono.empty();
                }
            }));

    }
//        CreditDto creditDto = externalService.externalFindCreditById (
//                                                            transactionCreditDto.getCreditNumber());
//        if (creditDto != null) {
//            TransactionCredit transactionCreditMono = mapper.map(transactionCreditDto,
//                                                        TransactionCredit.class);
//            transactionCreditRepository.save(transactionCreditMono);
//            Float amount = transactionCreditDto.getTransactionAmount();
//            if (transactionCreditDto.getTransactionType().equalsIgnoreCase("C")) {
//                amount = amount * -1;
//            }
//            creditDto.setCreditBalance(creditDto.getCreditBalance() + amount);
//            externalService.externalUpdateBalance(creditDto);
//            return Mono.empty();
//        } else {
//            return Mono.empty();
//        }
//    }

    @Override
    public Mono<TransactionCredit> update(TransactionCreditDto transactionCreditDto) {
        return transactionCreditRepository.existsById(transactionCreditDto.getCreditId())
            .flatMap(isExist -> {
                if (isExist) {
                    transactionCreditRepository.deleteById(transactionCreditDto.getTransactionId());
                    return this.save(transactionCreditDto);
                }
                return Mono.empty();

            });
    }

    @Override
    public Mono<Void> delete(Integer transactionId) {
        return transactionCreditRepository.findById(transactionId)
            .map(p->{
                Float amount = p.getTransactionAmount();
                if (p.getTransactionType().equalsIgnoreCase("A")){
                    amount = amount * -1;
                }
                return transactionCreditRepository.deleteById(p.getTransactionId());
            })
            .flatMap(a->transactionCreditRepository.deleteById(transactionId)
                .switchIfEmpty(Mono.empty()));
    }

    @Override
    public Mono<TransactionCredit> findById(Integer transactionId) {
        return transactionCreditRepository.findById(transactionId);
    }

    @Override
    public Flux<TransactionCredit> getAllBycreditNumber(String creditNumber) {
        return transactionCreditRepository.findAll()
            .filter(p->p.getCreditNumber() == creditNumber);
    }


}
