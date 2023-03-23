package com.Lending.ScenarySports.Controller;

import com.Lending.ScenarySports.Entity.Booking;
import com.Lending.ScenarySports.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/Bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    //crear nueva Reserva

    @PostMapping
    //devuelve un respuesta que sera la entidad

    public ResponseEntity<?> create(@RequestBody Booking booking)	{

        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.save(booking));

    }

    //leer un Reserva
    @GetMapping("/{id}")  //obtiene el id
    public ResponseEntity<Booking> read(@PathVariable int id){

        //si el id no existe devuelve el codigo 404
        Optional<Booking> optional_Booking = bookingService.findById(id);
        if (optional_Booking.isPresent()) {
            // si el id existe lo devuelve
            return ResponseEntity.ok(optional_Booking.get()) ;

        }else{
            return ResponseEntity.notFound().build();
        }

    }
    //modificar  Reserva
    @PutMapping("/{id}")
    public ResponseEntity<?> update (@RequestBody Booking booking, @PathVariable int id){

        Optional<Booking> Booking= bookingService.findById(id);
        if (Booking.isPresent()) {
            Booking.get().setFecha(booking.getDate());
            Booking.get().setHora(booking.getHora());
            Booking.get().setScenary(booking.getScenary());


            return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.save(Booking.get()));

        }
        return ResponseEntity.notFound().build();


    }

    //borrar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable int id){

        if (!bookingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        bookingService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    //Leer todos
    @GetMapping
    public List<Booking> readAll(){

        List<Booking> bookings = StreamSupport.stream(bookingService.findAll().spliterator(),false).
                collect(Collectors.toList());
        return bookings;
    }

}
