package com.Lending.ScenarySports.Services;

import com.Lending.ScenarySports.Entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookingService {
    public Iterable<Booking> findAll(); //devuelve una coleccion en forma de iterable

    public Page<Booking> findAll(Pageable pageable); //implementa la paginación, devueleve un objeto

    public Optional<Booking> findById(int id);  //devuelve Reservas  por id


    public Booking save(Booking booking); //guarda una reserva que pasemos

    public void deleteById(int id);  //elimina una reserva por id

}
