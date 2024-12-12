package com.company.tienda.controller;

import com.company.tienda.modelo.Categoria;
import com.company.tienda.response.*;
import com.company.tienda.services.ICategoriaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tiendaLibros/v1")
public class CategoriaRestController {

	@Autowired
	private ICategoriaServicio  servicio;
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/categoria")
	public ResponseEntity<RespuestaCategoriaRest> buscarCategoria(){
		ResponseEntity<RespuestaCategoriaRest> respuesta = servicio.buscar();
		return respuesta;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/categoria/{id}")
	public ResponseEntity<RespuestaCategoriaRest> buscarCategoriaId(@PathVariable Long id){
		ResponseEntity<RespuestaCategoriaRest> respuesta = servicio.buscarId(id);
		return respuesta;
	}
	
	/**
	 * 
	 * @param categoria
	 * @return
	 */
	@PostMapping("/categoria")
	public ResponseEntity<RespuestaCategoriaRest> guardar(@RequestBody Categoria categoria){
		ResponseEntity<RespuestaCategoriaRest> respuesta = servicio.guardar(categoria);
		return respuesta;
	}
	
	/**
	 * 
	 * @param categoria
	 * @param id
	 * @return
	 */
	@PutMapping("/categoria/{id}")
	public ResponseEntity<RespuestaCategoriaRest>actualizar(@RequestBody Categoria categoria, @PathVariable Long id){
		ResponseEntity<RespuestaCategoriaRest> respuesta = servicio.actualizar(categoria, id);
		return respuesta;
	}
	
}
