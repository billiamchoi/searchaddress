package com.dkbmc.searchaddress.repository;

import com.dkbmc.searchaddress.domain.Holiday;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    Optional<Holiday> findByLocDateAndHolidayIsTrue(LocalDate date);
    @Override
    @EntityGraph(attributePaths = {"company"})
    List<Holiday> findAll();

}
