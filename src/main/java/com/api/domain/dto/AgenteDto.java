package com.api.domain.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class AgenteDto implements Serializable {

	private static final long serialVersionUID = -552393812065471293L;

	private Integer codigo;

	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt", timezone="GMT-3" )
	private Date data;
	
	@JsonIgnore
	private List<ResultadoXMLRegiaoDto> regioes;

	public AgenteDto() {
		
	}

	public AgenteDto( Integer codigo, Date data ) {
		this.codigo = codigo;
		this.data = data;
	}
	
	public void addRegiao( ResultadoXMLRegiaoDto regiao ) {
		if ( this.regioes == null ) {
			setRegioes( new ArrayList<>() );
		}
		getRegioes().add( regiao );
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		AgenteDto other = (AgenteDto) obj;
		
		return Objects.equals( codigo, other.codigo );
	}

	@Override
	public int hashCode() {
		return Objects.hash( codigo );
	}
}
