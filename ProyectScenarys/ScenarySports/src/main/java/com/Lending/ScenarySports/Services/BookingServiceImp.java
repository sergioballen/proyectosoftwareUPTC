package com.Lending.ScenarySports.Services;

import com.Lending.ScenarySports.Entity.Booking;
import com.Lending.ScenarySports.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService{

    @Autowired     //inyecta el bookingRepository
    private BookingRepository bookingRepository;
    @Override
    @Transactional(readOnly = true)
    public Iterable<Booking> findAll() {

        return bookingRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Booking> findAll(Pageable pageable) {

        return bookingRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Booking> findById(int id) {

        return bookingRepository.findById(id);
    }

    @Override
    @Transactional
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<Booking> finAll() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }


}
