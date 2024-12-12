package com.company.tienda.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.tienda.dao.ICategoriaDao;
import com.company.tienda.modelo.Categoria;
import com.company.tienda.response.RespuestaCategoriaRest;

@Service
public class CategoriaServicioImp implements ICategoriaServicio {

	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<RespuestaCategoriaRest> buscar() {
		RespuestaCategoriaRest respuesta = new RespuestaCategoriaRest();
		try {
			
			List<Categoria> categoria =(List<Categoria>) categoriaDao.findAll(); 
			respuesta.getRepuestaCategoria().setCategoria(categoria);
			respuesta.setMetadata("Respuesta OK", "00", "Respuesta correcta");
			
		}catch(Exception ex) {
			
			respuesta.setMetadata("NOT OK", "-1", "Error consulta");
			ex.getStackTrace();
			return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<RespuestaCategoriaRest> buscarId(Long id){
		
		RespuestaCategoriaRest respuesta = new RespuestaCategoriaRest();
		List<Categoria> listaCategoria =new ArrayList<>();
		
		try {
			Optional<Categoria> categoria =  categoriaDao.findById(id);
			
			if(categoria.isPresent()) {
				listaCategoria.add(categoria.get());
				respuesta.getRepuestaCategoria().setCategoria(listaCategoria);
				respuesta.setMetadata("Respuesta OK", "00", "Respuesta correcta");
			}else {
				respuesta.setMetadata("NOT OK", "-1", "No se encuentra el ID de Categoria");
				return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception ex) {
			respuesta.setMetadata("NOT OK", "-1", "Error consulta por ID");
			ex.getStackTrace();
			return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.OK);
		
	}
}
