package com.company.tienda.controller;

import com.company.tienda.response.*;
import com.company.tienda.services.ICategoriaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tiendaLibros/v1")
public class CategoriaRestController {

	@Autowired
	private ICategoriaServicio  servicio;
	
	
	@GetMapping("/categoria")
	public ResponseEntity<RespuestaCategoriaRest> buscarCategoria(){
		ResponseEntity<RespuestaCategoriaRest> respuesta = servicio.buscar();
		return respuesta;
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<RespuestaCategoriaRest> buscarCategoriaId(@PathVariable Long id){
		ResponseEntity<RespuestaCategoriaRest> respuesta = servicio.buscarId(id);
		return respuesta;
	}
}
