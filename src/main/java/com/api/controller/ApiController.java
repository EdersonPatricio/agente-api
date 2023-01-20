package com.api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.domain.dto.ResultadoXMLRegiaoDto;
import com.api.domain.dto.ResultadoTabelaRegiaoDto;
import com.api.domain.model.Agente;
import com.api.domain.model.util.FileServiceUtil;
import com.api.domain.service.AgenteService;
import com.api.domain.service.RegiaoService;

import io.swagger.annotations.Api;

@CrossOrigin
@RestController
@RequestMapping( "/agente-api" )
@Api( tags = "Agente-api" )
public class ApiController {
	
	private static Logger LOG = Logger.getLogger( ApiController.class );
	
	@Autowired
	private AgenteService agenteService;
	
	@Autowired
	private RegiaoService regiaoService;
	
	@PostMapping( "/processarArquivos" )
	public ResponseEntity<Void> processarArquivos( @RequestParam MultipartFile file ) throws ParseException, IOException {
		LOG.info( this.getClass().getName() + " - processarArquivos - INICIO" );
		
		File arquivoConvertido = converterArquivo( file );
		
		List<Agente> agentes = FileServiceUtil.extrairDados( arquivoConvertido );
		
		agenteService.saveAll( agentes );
		
		LOG.info( this.getClass().getName() + " - processarArquivos - FIM" );
		
		return ResponseEntity.noContent().build();
	}

	private File converterArquivo( MultipartFile file ) throws FileNotFoundException, IOException {
		File arquivoConvertido = new File( file.getOriginalFilename() );
		
		FileOutputStream output = new FileOutputStream( arquivoConvertido );
		output.write( file.getBytes() );
		output.close();
		
		return arquivoConvertido;
	}
	
	@GetMapping( "/consultarSiglasRegioes" )
	public ResponseEntity<List<String>> consultarSiglasRegioes() {
		return ResponseEntity.ok( regiaoService.findSiglaGroupBySigla() );
	}

	@GetMapping( "/consultarPorRegiaoEstruturaXML/{sigla}" )
	public ResponseEntity<List<ResultadoXMLRegiaoDto>> consultarPorRegiaoEstruturaXML( @PathVariable String sigla ) {
		return ResponseEntity.ok( regiaoService.consultarPorRegiaoEstruturaXML( sigla ) );
	}
	
	@GetMapping( "/consultarPorRegiaoEstruturaTabela/{sigla}" )
	public ResponseEntity<List<ResultadoTabelaRegiaoDto>> consultarPorRegiaoEstruturaTabela( @PathVariable String sigla ) {
		return ResponseEntity.ok( regiaoService.consultarPorRegiaoEstruturaTabela( sigla ) );
	}
}
