package com.dkbmc.searchadress.api.rest.holiday;

import com.dkbmc.searchadress.api.rest.holiday.holidayResponse.HolidayResponseDTO;
import com.dkbmc.searchadress.api.rest.holiday.holidayResponse.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class HolidayAPI {

    @Value("${DATA.HOLIDAYKEY}")
    String key;

    WebClient webclient;


    public List<Item> holidayInfo() {
        LocalDate now = LocalDate.now();
        String year = String.valueOf(now.getYear());
        String nextMonth = String.valueOf(now.plusMonths(1).getMonthValue());
        String twoWordMonth = nextMonth.length() == 1 ? nextMonth.replace(nextMonth, "0" + nextMonth) : nextMonth;

        webclient = WebClient.builder()
                .baseUrl("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService")
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs().jaxb2Decoder(new Jaxb2XmlDecoder()))
                        .build())
                .build();
        Mono<HolidayResponseDTO> response;
        response = webclient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getHoliDeInfo")
                        .queryParam("solYear", "{year}")
                        .queryParam("solMonth", "{month}")
                        .queryParam("ServiceKey", "{serviceKey}")
                        .build(year, twoWordMonth, key))
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(HolidayResponseDTO.class);
        HolidayResponseDTO result = response.block();

        return Objects.requireNonNull(result).getBody().getItems().getItems();
    }
}
