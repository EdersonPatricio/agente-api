package com.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.model.Agente;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Long> {

}
