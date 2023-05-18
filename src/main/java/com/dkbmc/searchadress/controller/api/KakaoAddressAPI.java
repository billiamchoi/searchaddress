package com.dkbmc.searchadress.controller.api;

import com.dkbmc.searchadress.dto.ResponseDTO;
import com.dkbmc.searchadress.service.KakaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KakaoAddressAPI {

    public final KakaoService kakaoService;

    public KakaoAddressAPI(KakaoService kakaoService) {this.kakaoService = kakaoService;}

    @GetMapping(value = "/search", produces = "application/json; charset=UTF8")
    public ResponseEntity<String> addressSearch(@RequestParam String query){
        return ResponseEntity.ok(kakaoService.addressSearch(query));
    }
}
