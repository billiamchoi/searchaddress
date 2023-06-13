package com.dkbmc.searchaddress.service;

import com.dkbmc.searchaddress.dto.HolidayDTO;
import com.dkbmc.searchaddress.dto.ResponseDTO;
import com.dkbmc.searchaddress.externalApi.rest.holiday.HolidayAPI;
import com.dkbmc.searchaddress.externalApi.rest.holiday.holidayResponse.Item;
import com.dkbmc.searchaddress.domain.Holiday;
import com.dkbmc.searchaddress.repository.HolidayRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HolidayService extends BaseService {

    private final HolidayRepository holidayRepository;
    private final HolidayAPI holidayAPI;

    public HolidayService(HolidayRepository holidayRepository, HolidayAPI holidayAPI) {
        this.holidayRepository = holidayRepository;
        this.holidayAPI = holidayAPI;
    }

    public boolean isHoliday(LocalDate requestDay) {
        return holidayRepository.findByLocDateAndHolidayIsTrue(requestDay).isPresent();
    }

    public String insertHolidayFromOpenAPI() {
        List<Item> holidays = holidayAPI.holidayInfo();
        if (holidays.size() > 0) {
            bulkInsert(holidays);
        }
        return "ok";
}

    public void bulkInsert(List<Item> request) {
        List<Holiday> holidays = request.stream().map(item -> Holiday.builder()
                .holiday(item)
                .build()).toList();
        holidayRepository.saveAll(holidays);
    }

    public ResponseDTO getHolidays() {
        List<Holiday> holidays = holidayRepository.findAll();
        List<HolidayDTO> holidayDTOList = holidays.stream().map(HolidayDTO::new).toList();
        responseDTO = ResponseDTO.builder()
                .result(holidayDTOList)
                .return_msg("성공적으로 모든 공휴일 조회하였습니다.")
                .build();
        return responseDTO;
    }
}
