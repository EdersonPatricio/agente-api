package com.api.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table( name = "REGIAO" )
public class Regiao implements Serializable {
	
	private static final long serialVersionUID = 729713670818200561L;

	@Id
	@Column( name = "ID" )
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	@Column( name = "DES_SIGLA" )
	private String sigla;
	
	@Column( name = "VL_GERACAO", precision = 18, scale = 3 )
	private BigDecimal geracao;

	@Column( name = "VL_COMPRA", precision = 18, scale = 3 )
	private BigDecimal compra;

	@Column( name = "VL_PRECO_MEDIO", precision = 18, scale = 3 )
	private BigDecimal precoMedio;
	
	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "ID_AGENTE" )
	private Agente agente;
	
	public Regiao() {

	}

	public Regiao( String sigla, BigDecimal geracao, BigDecimal compra, BigDecimal precoMedio, Agente agente ) {
		this.sigla = sigla;
		this.geracao = geracao;
		this.compra = compra;
		this.precoMedio = precoMedio;
		this.agente = agente;
	}
	
	public Regiao( String sigla, String geracao, String compra, String precoMedio, Agente agente ) {
		this( sigla, new BigDecimal( geracao ), new BigDecimal( compra ), new BigDecimal( precoMedio ), agente );
	}
}
