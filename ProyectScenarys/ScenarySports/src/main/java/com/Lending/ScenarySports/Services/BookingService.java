package com.Lending.ScenarySports.Services;

import com.Lending.ScenarySports.Entity.Booking;
import com.Lending.ScenarySports.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    public Iterable<Booking> findAll(); //devuelve una coleccion en forma de iterable

    public Page<Booking> findAll(Pageable pageable); //implementa la paginación, devueleve un objeto

    public Optional<Booking> findById(int id);  //devuelve Reservas  por id


    public Booking save(Booking booking); //guarda una reserva que pasemos

    public void deleteById(int id);  //elimina una reserva por id
    public List<Booking> finAll();

    public List<Booking> getBookings();

    List<Booking> findByDateBetween(LocalDate startDate, LocalDate endDate);
    public List<Booking> findByUserId(int userCode);
}
