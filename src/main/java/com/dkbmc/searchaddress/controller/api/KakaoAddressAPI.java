package com.dkbmc.searchaddress.controller.api;

import com.dkbmc.searchaddress.service.KakaoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KakaoAddressAPI {

    public final KakaoService kakaoService;

    public KakaoAddressAPI(KakaoService kakaoService) {this.kakaoService = kakaoService;}

//    @GetMapping(value = "/search", produces = "application/json; charset=UTF8")
//    public ResponseEntity<String> addressSearch(@RequestParam String query){
//        return ResponseEntity.ok(kakaoService.addressSearch(query));
//    }
}
