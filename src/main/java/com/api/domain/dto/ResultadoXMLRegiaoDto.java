package com.api.domain.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ResultadoXMLRegiaoDto {
	
	private String sigla;
	
	private List<String> geracao;
	
	private List<String> compra;

	private List<String> precoMedio;
	
	private AgenteDto agente;

	public ResultadoXMLRegiaoDto() {

	}
	
	public ResultadoXMLRegiaoDto( String sigla, AgenteDto agente ) {
		this.sigla = sigla;
		this.agente = agente;
	}
	
	public void addGeracao( String valor ) {
		if ( this.geracao == null ) {
			setGeracao( new ArrayList<>() );
		}
		getGeracao().add( valor );
	}
	
	public void addCompra( String valor ) {
		if ( this.compra == null ) {
			setCompra( new ArrayList<>() );
		}
		getCompra().add( valor );
	}
	
	public void addPrecoMedio( String valor ) {
		if ( this.precoMedio == null ) {
			setPrecoMedio( new ArrayList<>() );
		}
		getPrecoMedio().add( valor );
	}
}
