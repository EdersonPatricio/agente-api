package com.api.domain.model.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.api.domain.dto.AgenteDto;
import com.api.domain.dto.ResultadoXMLRegiaoDto;
import com.api.domain.model.Agente;
import com.api.domain.model.Regiao;

public class FileServiceUtil {

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" );

	private static final String AGENTE = "agente";
	private static final String CODIGO = "codigo";
	private static final String DATA = "data";
	private static final String REGIAO = "regiao";
	private static final String GERACAO = "geracao";
	private static final String COMPRA = "compra";
	private static final String PRECO_MEDIO = "precoMedio";
	private static final String VALOR = "valor";
	
	private static List<AgenteDto> agentesDto = new ArrayList<AgenteDto>();
	
	public static List<Agente> extrairDados( File file ) {
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse( file );
			
			System.out.println( "Root element: " + document.getDocumentElement().getNodeName() );
			
			if ( document.hasChildNodes() ) {
				NodeList nodeList = document.getChildNodes();
				
				for ( int posicao = 0; posicao < nodeList.getLength(); posicao++ ) {
					Node elemNode = nodeList.item( posicao );
					extrairValores( elemNode );
				}
			}
		} catch ( Exception e ) {
			System.out.println( e.getMessage() );
		}
		
		return converterDadosParaEntidade();
	}
	
	private static void extrairValores( Node elemNode ) throws ParseException {
		AgenteDto agente = null;
		
		if ( elemNode.getNodeType() == Node.ELEMENT_NODE ) {
			extrairValoresAgente( elemNode, agente );
		}
	}
	
	private static AgenteDto extrairValoresAgente( Node elemNode, AgenteDto agente ) throws ParseException {
		if ( elemNode.getNodeType() == Node.ELEMENT_NODE ) {
			if ( elemNode.getNodeName().equals( AGENTE ) ) {
				agente = new AgenteDto();
			} else if ( elemNode.getNodeName().equals( CODIGO ) ) {
				System.out.println( "Codigo = " + elemNode.getTextContent() );
				agente.setCodigo( Integer.valueOf( elemNode.getTextContent() ) );
			} else if ( elemNode.getNodeName().equals( DATA ) ) {
				agente.setData( SIMPLE_DATE_FORMAT.parse( elemNode.getTextContent().replace( "T00:00:00-", "T00:00:00.000-" ) ) );
			} else if ( elemNode.getNodeName().equals( REGIAO ) ) {
				ResultadoXMLRegiaoDto regiao = new ResultadoXMLRegiaoDto();
				regiao = extrairValoresRegiao( elemNode, regiao );
				agente.addRegiao( regiao );
			}
			
			if ( elemNode.hasChildNodes() ) {
				NodeList nodeList = elemNode.getChildNodes();
				
				for ( int posicao = 0; posicao < nodeList.getLength(); posicao++ ) {
					Node node = nodeList.item( posicao );
					if ( !node.getNodeName().equals( "#text" ) && !node.getNodeName().equals( VALOR ) ) {
						agente = extrairValoresAgente( node, agente );
					}
				}
				if ( elemNode.getNodeName().equals( AGENTE ) ) {
					agentesDto.add( agente );
				}
			}
		}
		
		return agente;
	}
	
	private static ResultadoXMLRegiaoDto extrairValoresRegiao( Node elemNode, ResultadoXMLRegiaoDto regiao ) throws ParseException {
		if ( elemNode.getNodeType() == Node.ELEMENT_NODE ) {
			if ( elemNode.getNodeName().equals( "regiao" ) ) {
				regiao.setSigla( elemNode.getAttributes().item( 0 ).getNodeValue() );
			} else if ( elemNode.getNodeName().equals( GERACAO ) ) {
				regiao.setGeracao( Arrays.asList( elemNode.getTextContent().trim().replaceAll( "\\s+", ";" ).split( ";" ) ) );
			} else if ( elemNode.getNodeName().equals( COMPRA ) ) {
				regiao.setCompra( Arrays.asList( elemNode.getTextContent().trim().replaceAll( "\\s+", ";" ).split( ";" ) ) );
			} else if ( elemNode.getNodeName().equals( PRECO_MEDIO ) ) {
				regiao.setPrecoMedio( Arrays.asList( elemNode.getTextContent().trim().replaceAll( "\\s+", ";" ).split( ";" ) ) );
			}
			
			if ( elemNode.hasChildNodes() ) {
				NodeList nodeList = elemNode.getChildNodes();
				
				for ( int posicao = 0; posicao < nodeList.getLength(); posicao++ ) {
					Node node = nodeList.item( posicao );
					if ( !node.getNodeName().equals( "#text" ) && !node.getNodeName().equals( VALOR ) ) {
						regiao = extrairValoresRegiao( node, regiao );
					}
				}
			}
		}
		
		return regiao;
	}
	
	private static List<Agente> converterDadosParaEntidade() {
		List<Agente> agentes = new ArrayList<Agente>();
		
		for( AgenteDto agenteDto: agentesDto ) {
			Agente agente = new Agente( agenteDto.getCodigo(), agenteDto.getData() );
			
			List<Regiao> regioes = new ArrayList<Regiao>();
			
			for ( int i = 0; i < agenteDto.getRegioes().size(); i++ ) {
				ResultadoXMLRegiaoDto regiaoDto = agenteDto.getRegioes().get( i );
				
				for ( int j = 0; j < regiaoDto.getGeracao().size(); j++ ) {
					regioes.add( new Regiao( regiaoDto.getSigla(), regiaoDto.getGeracao().get( j ), regiaoDto.getCompra().get( j ), regiaoDto.getPrecoMedio().get( j ), agente ) );
				}
			}
			
			agente.setRegioes( regioes );
			agentes.add( agente );
		}
		
		return agentes;
	}
}
