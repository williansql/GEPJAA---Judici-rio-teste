package com.teste.gepjaa.hearing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface HearingSchedulingRepository extends JpaRepository<HearingScheduling, String> {
    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM HearingScheduling h WHERE h.court = :court AND h.location = :location AND h.dateTime = :dateTime")
    boolean existsByCourtAndLocationAndDateTime(@Param("court") String court, @Param("location") String location,
            @Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT h FROM HearingScheduling h WHERE h.district = :district AND DATE(h.dateTime) = DATE(:date)")
    java.util.List<HearingScheduling> findByDistrictAndDate(@Param("district") String district,
            @Param("date") java.time.LocalDateTime date);
}
