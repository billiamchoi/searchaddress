package com.dkbmc.searchaddress.controller.api;

import com.dkbmc.searchaddress.dto.HolidayDTO;
import com.dkbmc.searchaddress.dto.ResponseDTO;
import com.dkbmc.searchaddress.exception.NoDataFoundException;
import com.dkbmc.searchaddress.service.HolidayService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseDTO>  holidayInsert (){
        return ResponseEntity.ok().body(holidayService.insertHolidayFromOpenAPI());
    }

    @GetMapping(value = "/holidayInsertYearly")
    public ResponseEntity<ResponseDTO>  holidayInsertYearly (){
        return ResponseEntity.ok().body(holidayService.insertHolidayFromOpenAPILoop());
    }

    @GetMapping(value = "/holiday")
    public ResponseEntity<ResponseDTO> getHolidays(){
        return ResponseEntity.ok().body(holidayService.getHolidays());
    }

    @PostMapping(value = "/holiday")
    public ResponseEntity<ResponseDTO> createHoliday(@RequestBody HolidayDTO.RequestSave request){
        return ResponseEntity.ok().body(holidayService.createHoliday(request));
    }

    @DeleteMapping(value = "/holiday/{id}")
    public ResponseEntity<ResponseDTO> deleteHoliday(@PathVariable Long id){
        return ResponseEntity.ok().body(holidayService.deleteHoliday(id));
    }

    @GetMapping(value = "/holiday/{id}")
    public ResponseEntity<ResponseDTO> findHolidayById(@PathVariable Long id) throws NoDataFoundException {
        return ResponseEntity.ok().body(holidayService.findById(id));
    }

    @PutMapping(value = "/holiday")
    public ResponseEntity<ResponseDTO> updateHoliday(@RequestBody HolidayDTO.RequestUpdate request){
        return ResponseEntity.ok().body(holidayService.updateHoliday(request));
    }
}
