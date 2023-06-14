package com.dkbmc.searchaddress.service;

import com.dkbmc.searchaddress.domain.Company;
import com.dkbmc.searchaddress.dto.CompanyDTO;
import com.dkbmc.searchaddress.dto.HolidayDTO;
import com.dkbmc.searchaddress.dto.ResponseDTO;
import com.dkbmc.searchaddress.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends BaseService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public ResponseDTO getCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> companyDTOList = companies.stream().map(CompanyDTO::new).toList();
        responseDTO = ResponseDTO.builder()
                .result(companyDTOList)
                .return_msg("성공적으로 모든 회사 조회하였습니다.")
                .build();
        return responseDTO;
    }
}
