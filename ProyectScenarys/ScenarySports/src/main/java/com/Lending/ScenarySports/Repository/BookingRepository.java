package com.Lending.ScenarySports.Repository;

import com.Lending.ScenarySports.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
       List<Booking> findByDateAndHour(LocalDate date, LocalTime hour);

        //Booking findByDate(LocalDate fec);
        //Booking findByHour(LocalTime hour);
}
