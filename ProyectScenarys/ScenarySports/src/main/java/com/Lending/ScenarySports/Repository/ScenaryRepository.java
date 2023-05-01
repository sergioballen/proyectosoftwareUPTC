package com.Lending.ScenarySports.Repository;

import com.Lending.ScenarySports.Entity.Scenary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenaryRepository extends JpaRepository<Scenary,Integer> {
    }
