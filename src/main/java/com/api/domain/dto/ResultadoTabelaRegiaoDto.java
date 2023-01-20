package com.api.domain.dto;

import java.math.BigDecimal;

import com.api.domain.model.Regiao;

import lombok.Data;

@Data
public class ResultadoTabelaRegiaoDto {
	
	private String sigla;
	
	private BigDecimal geracao;

	private BigDecimal compra;

	private BigDecimal precoMedio;
	
	private AgenteDto agente;

	public ResultadoTabelaRegiaoDto() {

	}
	
	public ResultadoTabelaRegiaoDto( Regiao regiao ) {
		this.sigla = regiao.getSigla();
		this.agente = new AgenteDto( regiao.getAgente().getCodigo(), regiao.getAgente().getData() );
		this.geracao = regiao.getGeracao();
		this.compra = regiao.getCompra();
		this.precoMedio = regiao.getPrecoMedio();
	}
}
