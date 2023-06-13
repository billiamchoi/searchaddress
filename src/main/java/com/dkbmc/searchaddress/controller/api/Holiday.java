package com.dkbmc.searchaddress.controller.api;

import com.dkbmc.searchaddress.dto.ResponseDTO;
import com.dkbmc.searchaddress.service.HolidayService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/api")
public class Holiday {
    private final HolidayService holidayService;

    public Holiday(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    /**
     * 휴일 api 보내는 날짜가 휴일인지 확인 익월 까지의 데이터만 확인가능
     * @param requestDate (확인할 날짜 - type:LocalDate)
     * @return boolean
     */

    @GetMapping(value = "/isHoliday", produces = "application/json")
    public ResponseEntity<Boolean> isHoliday (@RequestParam("requestDate") String requestDate){
        return ResponseEntity.ok().body(holidayService.isHoliday(LocalDate.parse(requestDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
    }

    @GetMapping(value = "/holidayInsert")
    public ResponseEntity<String> holidayInsert (){
        return ResponseEntity.ok().body(holidayService.insertHolidayFromOpenAPI());
    }

    @GetMapping(value = "/holiday")
    public ResponseEntity<ResponseDTO> getHolidays(){
        return ResponseEntity.ok().body(holidayService.getHolidays());
    }
}
