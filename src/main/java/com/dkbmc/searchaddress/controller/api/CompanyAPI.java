package com.dkbmc.searchaddress.controller.api;

import com.dkbmc.searchaddress.dto.ResponseDTO;
import com.dkbmc.searchaddress.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class CompanyAPI {
    private final CompanyService companyService;

    public CompanyAPI(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company")
    public ResponseEntity<ResponseDTO> getCompanies(){
        return ResponseEntity.ok().body(companyService.getCompanies());
    }
}
