package com.DeportesUptc.Escenarios_Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DeportesUptc.Escenarios_Entity.Scenary_Sports;
import com.DeportesUptc.Escenarios_Repository.Scenary_Repository;

@Service
public class ScenaryServices_Implement implements  Scenary_Services{
	

	@Autowired  // inyecta el scenaryRepository
	private Scenary_Repository ScenaryRepository;
	

	@Override
	@Transactional(readOnly=true)  //metodo transaccional de solo lectura
	public Iterable<Scenary_Sports> findAll() {
		return ScenaryRepository.findAll();
	}

	@Override
	@Transactional(readOnly=true)  
	public Page<Scenary_Sports> findAll(Pageable pageable) {
		return ScenaryRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true) 
	public Optional<Scenary_Sports> findbyId(Long id) {
		return ScenaryRepository.findById(id);
	}

	@Override
	@Transactional
	public Scenary_Sports save(Scenary_Sports Scenary_sports) {
		return ScenaryRepository.save(Scenary_sports);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		ScenaryRepository.deleteById(id);
		
		
	}

}
