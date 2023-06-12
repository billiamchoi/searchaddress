package com.dkbmc.searchaddress.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class KakaoService {

    public String addressSearch(String query) {
        Mono<String> exchangeToMono = WebClient.builder().baseUrl("https://dapi.kakao.com")
                .build().get()
                .uri(uriBuilder -> uriBuilder.path("/v2/local/search/address.json")
                        .queryParam("query", query)
                        .build()
                )
                .header("Authorization", "KakaoAK " + "142818f11f36299a5e638927566f32c9")
                .exchangeToMono(response -> response.bodyToMono(String.class));
        return exchangeToMono.block();
    }
}
