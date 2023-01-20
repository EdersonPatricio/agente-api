package com.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.domain.model.Regiao;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Long> {

	public List<Regiao> findBySigla( String sigla );
	
	@Query( value = "SELECT DES_SIGLA FROM REGIAO GROUP BY DES_SIGLA ORDER BY DES_SIGLA", nativeQuery = true )
	public List<String> findSiglaGroupBySigla();
	
}
