package com.Lending.ScenarySports.Controller;

import com.Lending.ScenarySports.Entity.Booking;
import com.Lending.ScenarySports.Entity.User;
import com.Lending.ScenarySports.Repository.BookingRepository;
import com.Lending.ScenarySports.Repository.UserRepository;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/Bookings")
@CrossOrigin(origins = "https://uptcescenarios.netlify.app")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingService bookingService;


    //crear nueva Reserva

    @PostMapping("/Booking")
    //devuelve un respuesta que sera la entidad

    public ResponseEntity<?> create(@RequestBody Booking booking)	{

        LocalTime openingTime = LocalTime.of(8, 0); // Hora de apertura a las 8am
        LocalTime closingTime = LocalTime.of(20, 0); // Hora de cierre a las 8pm

        // Validar que la fecha de la reserva no sea una fecha pasada
        LocalDate currentDate = LocalDate.now();
        if (booking.getDate().isBefore(currentDate)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Debe ingresar una fecha valida para la reserva");
        }
        // Validar que el día de la reserva no sea domingo
        if (booking.getDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El dia Domingo no tenemos servicio de prestamo");
        }
        // Validar que la hora de la reserva esté entre las 8am y las 8pm
        if (booking.getHora().isBefore(openingTime) || booking.getHora().isAfter(closingTime)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El horario de resevas esta entre las 8am y las 8pm");
        }

          // Validar que no haya otra reserva en el mismo horario
        // Validar que no haya otra reserva en el mismo horario
        List<Booking> existingBookings = bookingRepository.findByDateAndHour(booking.getDate(), booking.getHora());
        for (Booking existingBooking : existingBookings) {
            if (existingBooking.getScenary().getId() == booking.getScenary().getId()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ya existe una reserva para el escenario en la fecha y hora especificadas, además debe haber una diferencia de 2 horas entre reservas");
            }
        }


        // Validar que el usuario no tenga una reserva futura
        List<Booking> userBookings = bookingService.findByUserId(booking.getUser().getUserCode());
        for (Booking userBooking : userBookings) {
            if (userBooking.getDate().isAfter(LocalDate.now())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Ya tienes una reserva futura pendiente, no puedes hacer otra reserva");
            }
        }
        // Obtener el usuario a partir de su ID
        User user = userRepository.findById(booking.getUser().getUserCode()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se pudo encontrar al usuario con el ID proporcionado");
        }

        // Añadir el nombre y la carrera del usuario a la respuesta
        booking.getUser().setName(user.getName());
        booking.getUser().setSchool(user.getSchool());

        Booking savedBooking = bookingService.save(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);


    }

    //leer un Reserva
    @GetMapping("/Booking/{id}")  //obtiene el id
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
    @PutMapping("/Booking/{id}")
    public ResponseEntity<?> update (@RequestBody Booking booking, @PathVariable int id){

        Optional<Booking> Booking= bookingService.findById(id);

        if (Booking.isPresent()) {

                // Validar que la fecha de la reserva sea actual o en el futuro
                LocalDate today = LocalDate.now();
                if (booking.getDate().isBefore(today)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("No se puede actualizar una reserva con una fecha pasada");
                }

                // Validar que el día de la reserva no sea domingo
                if (booking.getDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("No se puede hacer reservas para los domingos");
                }

                // Validar que la hora de la reserva esté entre las 8am y las 8pm
                LocalTime openingTime = LocalTime.of(8, 0);
                LocalTime closingTime = LocalTime.of(20, 0);
                if (booking.getHora().isBefore(openingTime) || booking.getHora().isAfter(closingTime)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Las reservas solo están disponibles entre las 8am y las 8pm");
                }

                // Validar que no haya otra reserva en el mismo horario
            List<Booking> existingBookings = bookingRepository.findByDateAndHour(booking.getDate(), booking.getHora());
            for (Booking existingBooking : existingBookings) {
                if (existingBooking.getScenary().getId() == booking.getScenary().getId()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body("Ya existe una reserva para el escenario en la fecha y hora especificadas, además debe haber una diferencia de 2 horas entre reservas");
                }
            }
            Booking.get().setFecha(booking.getDate());
            Booking.get().setHora(booking.getHora());
            Booking.get().setScenary(booking.getScenary());


            return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.save(Booking.get()));

        }
        return ResponseEntity.notFound().build();


    }

    //borrar reserva
    @DeleteMapping("/Booking/{id}")
    public ResponseEntity<?> deleteB (@PathVariable int id){

        if (!bookingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        bookingService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    //Leer todos
    @GetMapping("/Booking")
    public List<Booking> readAll(){

        List<Booking> bookings = StreamSupport.stream(bookingService.findAll().spliterator(),false).
                collect(Collectors.toList());
        return bookings;
    }



    @PostMapping("/Booking/exportExcel")
    public void exportBookings(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                               @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                               HttpServletResponse response) throws IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

        response.setContentType("application/octet-stream");
        String dateActual = dateFormat.format(new Date());

        String head = "Content-Disposition";
        String value = "attachment; filename=Booking_" + dateActual + ".xlsx";
        response.setHeader(head, value);

        List<Booking> bookings = bookingService.findByDateBetween(start, end);
        ReportService exporter = new ReportService(bookings);
        exporter.Export(response);
    }

}
