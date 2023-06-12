package com.dkbmc.searchaddress.service;

import com.dkbmc.searchaddress.domain.Address;
import com.dkbmc.searchaddress.dto.AddressDTO;
import com.dkbmc.searchaddress.dto.ResponseDTO;
import com.dkbmc.searchaddress.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AddressService extends BaseService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public int saveAddress(AddressDTO.RequestSave request) {
       Address address =  addressRepository.save(request.toEntity());
        if (address.getId() != null) {
            result = 1;
        }
        return result;
    }

    public ResponseDTO saveAddressRes(AddressDTO.RequestSave request) {
        int res = saveAddress(request);
        if (res == 1) {
            responseDTO = ResponseDTO.builder()
                    .result(res)
                    .return_msg("성공적으로 주소 저장 완료")
                    .build();
        } else {
            responseDTO = ResponseDTO.builder()
                    .error_code("500")
                    .error_msg("주소 저장 실패")
                    .build();
        }
        return responseDTO;
    }

    public String addressSearch(String query, int pageNum) {
        Mono<String> exchangeToMono = WebClient.builder().baseUrl("https://business.juso.go.kr")
                .build().get()
                .uri(uriBuilder -> uriBuilder.path("/addrlink/addrLinkApi.do")
                        .queryParam("keyword", query)
                        .queryParam("currentPage", pageNum)
                        .queryParam("countPerPage", 10)
                        .queryParam("confmKey", "devU01TX0FVVEgyMDIzMDUxOTEwMDUxOTExMzc4OTA=")
                        .queryParam("hstryYn", "Y")
                        .queryParam("resultType", "json")
                        .build()
                )
                .exchangeToMono(response -> response.bodyToMono(String.class));
        return exchangeToMono.block();
    }
}
