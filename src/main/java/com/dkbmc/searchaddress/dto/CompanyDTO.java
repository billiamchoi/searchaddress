package com.dkbmc.searchaddress.dto;

import com.dkbmc.searchaddress.domain.Company;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyDTO {
    private Long id;
    private String name;

    @Builder
    public CompanyDTO(Company entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
