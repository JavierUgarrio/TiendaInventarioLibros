package com.company.tienda.services;

import org.springframework.http.ResponseEntity;

import com.company.tienda.response.RespuestaCategoriaRest;

public interface ICategoriaServicio {

	public ResponseEntity<RespuestaCategoriaRest> buscar();
	
	public ResponseEntity<RespuestaCategoriaRest> buscarId(Long id);
}

