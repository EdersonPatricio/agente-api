package com.api.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@Entity
@Table( name = "AGENTE" )
@XStreamAlias("agente")
public class Agente implements Serializable {

	private static final long serialVersionUID = -552393812065471293L;

	@Id
	@Column( name = "ID" )
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	@Column( name = "COD_CODIGO" )
	private Integer codigo;

	@Column( name = "DT_DATA" )
	@Temporal( TemporalType.TIMESTAMP )
	private Date data;
	
	@OneToMany( mappedBy = "agente", cascade = CascadeType.PERSIST )
	private List<Regiao> regioes;
	
	public Agente() {
		
	}

	public Agente( Integer codigo, Date data ) {
		this.codigo = codigo;
		this.data = data;
	}
	
	public void addRegiao( Regiao regiao ) {
		if ( this.regioes == null ) {
			setRegioes( new ArrayList<>() );
		}
		getRegioes().add( regiao );
	}
}
