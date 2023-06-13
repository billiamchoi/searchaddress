package com.dkbmc.searchaddress.domain;

import com.dkbmc.searchaddress.externalApi.rest.holiday.holidayResponse.Item;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(30)")
    private String dateName;

    @Column(columnDefinition = "boolean")
    private Boolean holiday;

    private LocalDate locDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder
    public Holiday(Item holiday) {
        this.dateName = holiday.getDateName();
        this.holiday = holiday.getIsHoliday().equals("Y");
        this.locDate = LocalDate.parse(String.valueOf(holiday.getLocdate()), DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
