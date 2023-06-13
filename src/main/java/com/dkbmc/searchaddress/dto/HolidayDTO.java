package com.dkbmc.searchaddress.dto;

import com.dkbmc.searchaddress.domain.Company;
import com.dkbmc.searchaddress.domain.Holiday;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
public class HolidayDTO {
    private String dateName;
    private Boolean holiday;
    private LocalDate locDate;
    private Company company;

    @Builder
    public HolidayDTO(Holiday entity) {
        this.dateName = entity.getDateName();
        this.holiday = entity.getHoliday();
        this.locDate = entity.getLocDate();
        this.company = entity.getCompany();
    }

    @Getter
    @NoArgsConstructor
    public static class RequestSave {
        private String dateName;
        private Boolean holiday;
        private LocalDate locDate;
        private Company company;

        @Builder
        public RequestSave(String dateName, Boolean holiday, LocalDate locDate, Company company) {
            this.dateName = dateName;
            this.holiday = holiday;
            this.locDate = locDate;
            this.company = company;
        }
    }
}
