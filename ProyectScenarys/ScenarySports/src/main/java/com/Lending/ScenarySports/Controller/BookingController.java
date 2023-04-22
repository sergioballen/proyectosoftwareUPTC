package com.Lending.ScenarySports.Controller;

import com.Lending.ScenarySports.Entity.Booking;
import com.Lending.ScenarySports.Entity.User;
import com.Lending.ScenarySports.Repository.BookingRepository;
import com.Lending.ScenarySports.Services.BookingService;
import com.Lending.ScenarySports.Services.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.plaf.synth.SynthFormattedTextFieldUI;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/Bookings")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;


    //crear nueva Reserva

    @PostMapping
    //devuelve un respuesta que sera la entidad

    public ResponseEntity<?> create(@RequestBody Booking booking)	{

      //  Booking dateExist = bookingRepository.findByDate(booking.getDate());
        //Booking hourExist = bookingRepository.findByHour(booking.getHora());
        //List<Booking> existingBookings = bookingRepository.findByDateAndHour(booking.getDate(),booking.getHora());
        List<Booking> existingBookings = bookingRepository.findByDate(booking.getDate());
        for (Booking existingBooking : existingBookings) {
            LocalDateTime existingDateTime = LocalDateTime.of(existingBooking.getDate(), existingBooking.getHora());
            LocalDateTime newDateTime = LocalDateTime.of(booking.getDate(), booking.getHora());

            if (existingDateTime.minusHours(2).isBefore(newDateTime) && existingDateTime.plusHours(2).isAfter(newDateTime)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ya existe una reserva para la fecha y hora especificadas, además debe haber una diferencia de 2 horas entre reservas");
            }
        }

        Booking savedBooking = bookingService.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);


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



    @PostMapping("/exportExcel")
    public void exportBookings(HttpServletResponse response) throws IOException {


        DateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String Date_start =dateFormat.format(new Date());
        String Date_end =dateFormat.format(new Date());
        User user = null;
        // Map<String, Object> response = new HashMap();


        response.setContentType("application/octet-stream");

        String dateActual =dateFormat.format(new Date());

        String head= "Content-Disposition";
        String value="attachment; filename=Booking_"+dateActual+ ".xlsx";

        response.setHeader(head,value);

        List<Booking> bookings = bookingService.finAll();
        ReportService exporter = new ReportService(bookings);
        exporter.Export(response);

    }

}
