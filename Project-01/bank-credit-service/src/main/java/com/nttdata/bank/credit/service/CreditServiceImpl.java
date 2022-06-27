package com.nttdata.bank.credit.service;

import com.nttdata.bank.credit.model.entity.document.Credit;
import com.nttdata.bank.credit.model.entity.dto.ClientDto;
import com.nttdata.bank.credit.model.entity.dto.CreditDto;
import com.nttdata.bank.credit.model.entity.dto.ProductDto;
import com.nttdata.bank.credit.model.repository.CreditRepository;
import com.nttdata.bank.credit.model.service.CreditService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private Mapper mapper;

    @Autowired
    private ExternalService externalService;

    @Override
    public Flux<Credit> getAll() {
        return creditRepository.findAll();
    }

    @Override
    public Mono<Credit> save( Integer clientId, Integer productId, CreditDto creditDto) {
        Credit creditMono = mapper.map(creditDto, Credit.class);
        return creditRepository.save(creditMono);
    }

    @Override
    public Mono<Credit> update(CreditDto creditDto) {
        Credit creditMono = mapper.map(creditDto, Credit.class);
        return creditRepository.save(creditMono);
    }

    @Override
    public Mono<Void> delete(Integer creditId) {
        return creditRepository.deleteById(creditId);
    }

    @Override
    public Mono<Credit> findById(Integer creditId) {
        return this.creditRepository.findById(creditId);
    }

    @Override
    public Flux<Credit> findCreditByClientId(Integer clientId) {
        return creditRepository.findAll()
                .filter(p->p.getClientId()==clientId);
    }

    @Override
    public Mono<ClientDto> findClientByClientId(Integer clientId){
        return externalService.findClientByClientId(clientId);
    }

    @Override
    public Mono<ProductDto> findProductByProductId(Integer productId){
        return externalService.findProductByProductId(productId);
    }
}
