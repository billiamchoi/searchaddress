package com.dkbmc.searchaddress.dto;

import com.dkbmc.searchaddress.domain.Company;
import com.dkbmc.searchaddress.domain.Holiday;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
public class HolidayDTO {
    private Long id;
    private String dateName;
    private Boolean holiday;
    private LocalDate locDate;
    private Company company;

    @Builder
    public HolidayDTO(Holiday entity) {
        this.id = entity.getId();
        this.dateName = entity.getDateName();
        this.holiday = entity.getHoliday();
        this.locDate = entity.getLocDate();
        if (entity.getCompany() != null) {
            this.company = Company.builder()
                    .id(entity.getCompany().getId())
                    .name(entity.getCompany().getName())
                    .build();
        }

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

        public Holiday toEntity() {
            return new Holiday(this.dateName,this.holiday, this.locDate, this.company);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class RequestUpdate {
        private Long id;
        private String dateName;
        private Boolean holiday;
        private LocalDate locDate;
        private Company company;

        @Builder
        public RequestUpdate(Long id, String dateName, Boolean holiday, LocalDate locDate, Company company) {
            this.id = id;
            this.dateName = dateName;
            this.holiday = holiday;
            this.locDate = locDate;
            this.company = company;
        }

        public Holiday toEntity() {
            return new Holiday(this. id, this.dateName,this.holiday, this.locDate, this.company);
        }
    }
}
