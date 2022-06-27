package com.nttdata.bank.credit.service;

import com.nttdata.bank.credit.model.entity.dto.ClientDto;
import com.nttdata.bank.credit.model.entity.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final WebClient webClient = WebClient
                                         .builder()
                                         .baseUrl("http://localhost:8001")
                                         .build();

    private final WebClient webProduct = WebClient
                                        .builder()
                                        .baseUrl("http://localhost:8002")
                                        .build();

    public Mono<ClientDto> findClientByClientId(Integer clientId){
        return webClient.get()
                         .uri("/clients/{clientId}", clientId)
                         .accept(MediaType.APPLICATION_JSON)
                         .retrieve()
                         .bodyToMono(ClientDto.class);
    }

    public Mono<ProductDto> findProductByProductId(Integer productId){
        return webProduct.get()
                .uri("/products/{productId}", productId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ProductDto.class);
    }
}

