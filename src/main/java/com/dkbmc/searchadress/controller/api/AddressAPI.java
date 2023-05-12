package com.dkbmc.searchadress.controller.api;

import com.dkbmc.searchadress.dto.AddressDTO;
import com.dkbmc.searchadress.dto.ResponseDTO;
import com.dkbmc.searchadress.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
