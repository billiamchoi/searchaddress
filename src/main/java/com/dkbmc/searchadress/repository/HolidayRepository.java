package com.dkbmc.searchadress.repository;

import com.dkbmc.searchadress.domain.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    Optional<Holiday> findByLocDate(LocalDate date);
}
