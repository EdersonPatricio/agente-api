package com.api.domain.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.dto.AgenteDto;
import com.api.domain.dto.ResultadoXMLRegiaoDto;
import com.api.domain.dto.ResultadoTabelaRegiaoDto;
import com.api.domain.model.Regiao;
import com.api.domain.repository.RegiaoRepository;

@Service
public class RegiaoService {
	
	@Autowired
	private RegiaoRepository regiaoRepository;
	
	public List<ResultadoXMLRegiaoDto> consultarPorRegiaoEstruturaXML( String sigla ) {
		List<Regiao> regioes = regiaoRepository.findBySigla( sigla );
		
		Map<AgenteDto, ResultadoXMLRegiaoDto> mapa = new LinkedHashMap<AgenteDto, ResultadoXMLRegiaoDto>();
		
		ResultadoXMLRegiaoDto regiaoDto = null;

		for ( Regiao regiao : regioes ) {
			AgenteDto agenteDto = new AgenteDto( regiao.getAgente().getCodigo(), regiao.getAgente().getData() );
			
			if ( !mapa.containsKey( agenteDto ) ) {
				regiaoDto = new ResultadoXMLRegiaoDto( sigla, agenteDto );
				mapa.put( agenteDto, regiaoDto );
			}

			regiaoDto.setAgente( agenteDto );
			regiaoDto.addGeracao( regiao.getGeracao().toString() );
			regiaoDto.addCompra( regiao.getCompra().toString() );
			regiaoDto.addPrecoMedio( regiao.getPrecoMedio().toString() );
		}
		
		List<ResultadoXMLRegiaoDto> regioesDto = new ArrayList<ResultadoXMLRegiaoDto>();

		mapa.forEach( (key, value) -> regioesDto.add( value ) );
		
		return regioesDto;
	}
	
	public List<ResultadoTabelaRegiaoDto> consultarPorRegiaoEstruturaTabela( String sigla ) {
		List<Regiao> regioes = regiaoRepository.findBySigla( sigla );
		
		List<ResultadoTabelaRegiaoDto> resultadoTabelaRegiaoDtos = new ArrayList<ResultadoTabelaRegiaoDto>();

		regioes.forEach( regiao -> resultadoTabelaRegiaoDtos.add( new ResultadoTabelaRegiaoDto( regiao ) ) );

		return resultadoTabelaRegiaoDtos;
	}
	
	public List<String> findSiglaGroupBySigla() {
		return regiaoRepository.findSiglaGroupBySigla();
	}
}
