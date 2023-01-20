package com.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.model.Agente;
import com.api.domain.repository.AgenteRepository;

@Service
public class AgenteService {
	
	@Autowired
	private AgenteRepository agenteRepository;
	
	public List<Agente> saveAll( List<Agente> agentes ) {
		return agenteRepository.saveAll( agentes );
	}
	
}
