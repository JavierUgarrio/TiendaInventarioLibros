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
	
	@Override
	@Transactional()
	public ResponseEntity<RespuestaCategoriaRest> guardar(Categoria categoria){
		
		RespuestaCategoriaRest respuesta = new RespuestaCategoriaRest();
		List<Categoria> listaCategoria =new ArrayList<>();
		
		try {
			
			Categoria categoriaGuardada = categoriaDao.save(categoria);
			if(categoriaGuardada != null) {
				listaCategoria.add(categoriaGuardada);
				respuesta.getRepuestaCategoria().setCategoria(listaCategoria);
				respuesta.setMetadata("Respuesta OK", "00", "Respuesta correcta");
							
			}else {
				respuesta.setMetadata("NOT OK", "-1", "Categoria no guardada");
				return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.BAD_REQUEST);
			}	
		}catch(Exception ex) {
			respuesta.setMetadata("NOT OK", "-1", "Error guardar la categoria");
			ex.getStackTrace();
			return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.OK);
	}
	
	@Override
	@Transactional()
	public ResponseEntity<RespuestaCategoriaRest> actualizar(Categoria categoria, Long id){
		RespuestaCategoriaRest respuesta = new RespuestaCategoriaRest();
		List<Categoria> listaCategorias = new ArrayList<>();
		
		try {
			Optional<Categoria> categoriaBuscar =  categoriaDao.findById(id);
			if(categoriaBuscar.isPresent()) {
				//se procedera a actualizar la categoria
				categoriaBuscar.get().setNombre(categoria.getNombre());
				categoriaBuscar.get().setDescripcion(categoria.getDescripcion());
				Categoria categoriaActualizada = categoriaDao.save(categoriaBuscar.get());
				
				if(categoriaActualizada != null) {
					listaCategorias.add(categoriaActualizada);
					respuesta.getRepuestaCategoria().setCategoria(listaCategorias);
					respuesta.setMetadata("Respuesta OK", "00", "Categoria actualizada");
				}else {
					respuesta.setMetadata("NOT OK", "-1", "Error en la actualizacion");
					return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.BAD_REQUEST);
				}
			}else {
				respuesta.setMetadata("NOT OK", "-1", "Categoria no encontrada");
				return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception ex) {
			respuesta.setMetadata("NOT OK", "-1", "Error actualizar categoria");
			ex.getStackTrace();
			return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.OK);
				
	}
	
	@Override
	@Transactional()
	public ResponseEntity<RespuestaCategoriaRest> eliminar(Long id){
		RespuestaCategoriaRest respuesta = new RespuestaCategoriaRest();
		List<Categoria> listaCategorias = new ArrayList<>();
		
		try {
			categoriaDao.deleteById(id);
			respuesta.setMetadata("Respuesta OK", "00", "Categoria eliminada");
			
		}catch(Exception ex) {
			respuesta.setMetadata("NOT OK", "-1", "Error eliminar por ID");
			ex.getStackTrace();
			return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<RespuestaCategoriaRest>(respuesta, HttpStatus.OK);
	}
}
