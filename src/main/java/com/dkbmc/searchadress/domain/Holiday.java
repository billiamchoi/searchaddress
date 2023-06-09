package com.dkbmc.searchadress.domain;

import com.dkbmc.searchadress.api.rest.holiday.holidayResponse.Item;
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

    @Builder
    public Holiday(Item holiday) {
        this.dateName = holiday.getDateName();
        this.holiday = holiday.getIsHoliday().equals("Y");
        this.locDate = LocalDate.parse(String.valueOf(holiday.getLocdate()), DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

}
