package com.dkbmc.searchaddress.controller.api;

import com.dkbmc.searchaddress.dto.AddressDTO;
import com.dkbmc.searchaddress.dto.ResponseDTO;
import com.dkbmc.searchaddress.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AddressAPI {

    private final AddressService addressService;

    public AddressAPI(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/address")
    public ResponseEntity<ResponseDTO> createAddress(@RequestBody AddressDTO.RequestSave request){
        return ResponseEntity.ok(addressService.saveAddressRes(request));
    }

    @GetMapping(value = "/search", produces = "application/json; charset=UTF8")
    public ResponseEntity<String> addressSearch(@RequestParam String query, @RequestParam int pageNum){
        return ResponseEntity.ok(addressService.addressSearch(query, pageNum));
    }
}
