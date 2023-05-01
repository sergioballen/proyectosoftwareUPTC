package com.Lending.ScenarySports.Entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public int id;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Column(name = "Date", nullable = false)
    public LocalDate date;
    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "Hour", nullable = false)
    public LocalTime hour;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "User", nullable = false)
    public User user;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "Scenary", nullable = false)
    public Scenary scenary;

    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Scenary getScenary() {
        return scenary;
    }

    public void setScenary(Scenary scenary) {
        this.scenary = scenary;
    }

    public LocalDate getDate() {

        return date;
    }

    public void setFecha(LocalDate date) {

        this.date = date;
    }

    public LocalTime getHora() {
        return hour;
    }

    public void setHora(LocalTime hora) {
        this.hour = hour;
    }
}
