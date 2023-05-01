package com.Lending.ScenarySports.Services;

import com.Lending.ScenarySports.Entity.Scenary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ScenaryService {
    public Iterable<Scenary> findAll(); //devuelve una coleccion en forma de iterable

    public Page<Scenary> findAll(Pageable pageable); //implementa la paginación, devueleve un objeto

    public Optional<Scenary> findById(int id);  //devuelve Escenarios  por id


    public Scenary save(Scenary scenary); //guarda un escenario que pasemos

    public void deleteById(int id);  //elimina un escenario por id

}

