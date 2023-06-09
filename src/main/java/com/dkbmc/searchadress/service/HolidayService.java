package com.dkbmc.searchadress.service;

import com.dkbmc.searchadress.api.rest.holiday.HolidayAPI;
import com.dkbmc.searchadress.api.rest.holiday.holidayResponse.Item;
import com.dkbmc.searchadress.domain.Holiday;
import com.dkbmc.searchadress.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final HolidayAPI holidayAPI;

    public HolidayService(HolidayRepository holidayRepository, HolidayAPI holidayAPI) {
        this.holidayRepository = holidayRepository;
        this.holidayAPI = holidayAPI;
    }

    public boolean isHoliday(LocalDate requestDay) {
        return holidayRepository.findByLocDate(requestDay).isPresent();
    }

    public String insertHoliday() {
        List<Item> holidays = holidayAPI.holidayInfo();
        if (holidays.size() > 0) {
            insert(holidays);
        }
        return "ok";
    }

    public void insert(List<Item> request) {
        List<Holiday> holidays = request.stream().map(item -> {
            return Holiday.builder()
                    .holiday(item)
                    .build();
        }).toList();
        holidayRepository.saveAll(holidays);
    }

}