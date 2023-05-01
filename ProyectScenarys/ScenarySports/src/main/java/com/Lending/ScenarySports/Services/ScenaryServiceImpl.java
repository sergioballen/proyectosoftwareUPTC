package com.Lending.ScenarySports.Services;

import com.Lending.ScenarySports.Entity.Booking;
import com.Lending.ScenarySports.Entity.Scenary;
import com.Lending.ScenarySports.Repository.ScenaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class ScenaryServiceImpl implements ScenaryService {

    @Autowired   // inyecta el scenaryRepository
    private ScenaryRepository scenaryRepository;

    @Override
    @Transactional(readOnly = true)  //metodo transaccional de solo lectura
    public Iterable<Scenary> findAll() {
        return scenaryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Scenary> findAll(Pageable pageable) {
        return scenaryRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Scenary> findById(int id) {
        return scenaryRepository.findById(id);
    }

    @Override
    @Transactional
    public Scenary save(Scenary scenary) {

        return scenaryRepository.save(scenary);
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        scenaryRepository.deleteById(id);
    }




}
