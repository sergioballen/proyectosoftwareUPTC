package com.DeportesUptc.Escenarios_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DeportesUptc.Escenarios_Entity.Scenary_Sports;

@Repository
public interface Scenary_Repository extends JpaRepository<Scenary_Sports, Long> {

}
