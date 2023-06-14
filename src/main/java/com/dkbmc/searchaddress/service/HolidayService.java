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
import java.util.Optional;

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

    public ResponseDTO insertHolidayFromOpenAPI() {
        List<Item> holidays = holidayAPI.holidayInfo();
        if (holidays != null) {
            bulkInsert(holidays);
        }
        return responseDTO = ResponseDTO.builder()
                .return_msg("성공적으로 다음달 국경일 추가 완료하였습니다.")
                .build();
    }

    public ResponseDTO insertHolidayFromOpenAPILoop() {
        LocalDate now = LocalDate.now();
        String year = String.valueOf(now.getYear());
        for(int month = 1; month <= 12; month++) {
            List<Item> holidays = holidayAPI.certainHolidayInfo(month);
            if (holidays != null) {
                bulkInsert(holidays);
            }
        }

        return responseDTO = ResponseDTO.builder()
                .return_msg("성공적으로 다음달 국경일 추가 완료하였습니다.")
                .build();
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

    public ResponseDTO createHoliday(HolidayDTO.RequestSave request) {
        Holiday h = holidayRepository.save(request.toEntity());
        responseDTO = ResponseDTO.builder()
                .result(h)
                .return_msg("성공적으로 모든 공휴일 생성하였습니다.")
                .build();
        return responseDTO;
    }

    public ResponseDTO deleteHoliday(Long id) {
        holidayRepository.deleteById(id);
        responseDTO = ResponseDTO.builder()
                .return_msg("성공적으로 해당 공휴일 삭제하였습니다.")
                .build();
        return responseDTO;
    }

    public ResponseDTO findById(Long id) {
        Optional<Holiday> h = holidayRepository.findById(id);
        HolidayDTO holidayDTO = new HolidayDTO(h.get());
        responseDTO = ResponseDTO.builder()
                .result(holidayDTO)
                .return_msg("성공적으로 공휴일 조회하였습니다.")
                .build();
        return responseDTO;
    }

    public ResponseDTO updateHoliday(HolidayDTO.RequestUpdate request) {
        Holiday h = holidayRepository.save(request.toEntity());
        responseDTO = ResponseDTO.builder()
                .return_msg("성공적으로 모든 공휴일 수정하였습니다.")
                .build();
        return responseDTO;
    }
}
