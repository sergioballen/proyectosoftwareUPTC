package com.DeportesUptc.Escenarios_Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.DeportesUptc.Escenarios_Entity.Scenary_Sports;


public interface Scenary_Services {
public Iterable<Scenary_Sports> findAll(); //devuelve una coleccion en forma de iterable
	
	public Page<Scenary_Sports> findAll(Pageable pageable); //implementa la paginación, devueleve un objeto
	
	public Optional<Scenary_Sports> findbyId(Long id);  //devuelve usuarios  por id
	
	public Scenary_Sports save(Scenary_Sports Scenary_sport); //guarda un escenario que pasemos
	
	public void deleteById(Long id);  //elimina un escenario por id
}
