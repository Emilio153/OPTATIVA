package com.daw.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Estado;
import com.daw.persistence.entities.Tarea;
import com.daw.persistence.repositories.TareaRepository;
import com.daw.service.exceptions.TareaException;
import com.daw.service.exceptions.TareaNotFoundException;

@Service

public class TareaService  {
	@Autowired
	private TareaRepository tareaRepository;
	// Obtener todas las tareas.
	public List<Tarea> findAll(){
		return this.tareaRepository.findAll();
	}
	// Obtener una tarea mediante su ID.

	public Tarea findById (int idTarea) {
		return this.tareaRepository.findById(idTarea).get();
		
	}
	
	// Crear una tarea.

	public Tarea create(Tarea tarea) {
		if(tarea.getFechaVencimiento().isBefore(LocalDate.now())) {
		 throw new TareaException("La fecha de vencimiento debe de ser posterior");
		}
		tarea.setIdTarea(0);
		tarea.setEstado(Estado.PENDIENTE);
		tarea.setFechaCreacion(LocalDate.now());
		return this.tareaRepository.save(tarea);
	}
	// Modificar una tarea.

	public Tarea update(Tarea tarea, int idTarea) {
		if(tarea.getIdTarea() != idTarea) {
			throw new TareaException("El id del body () y el id del path () no coinciden"); 
			
		}
		if(!this.tareaRepository.existsById(idTarea)) {
			throw new TareaException("El id de la tarea no existe") ;
		}
		if(tarea.getEstado() != null) {
			throw new TareaException("No se puede modificar el estado");
		}
		if(tarea.getFechaCreacion() != null) {
			throw new TareaException("No se puede modificar la fecha de creación");
		} // RECUPERO LA TAREA QUE ESTA EN LA BBDD Y MODIFICO SOLO LOS CAMPOS PERMITIDOS
		  // SI GUARDO DIRECTAMENTE TAREA
		Tarea tareaBD = this.findById(idTarea);
		tareaBD.setDescripcion(tarea.getDescripcion());
		tareaBD.setTitulo(tarea.getTitulo());
		tareaBD.setFechaVencimiento(tarea.getFechaVencimiento());
		
		return this.tareaRepository.save(tarea);
	}
	
	
	// Borrar una tarea.

	public void delete(int idTarea) {
		if(!this.tareaRepository.existsById(idTarea)) {
			throw new TareaNotFoundException("La tarea no existe");
			
		}
		this.tareaRepository.deleteById(idTarea);
		
	}
	// Iniciar una tarea (solo se pueden iniciar tareas PENDIENTES).

	public Tarea marcarEnProgreso(int idTarea) {
		Tarea tarea = this.findById(idTarea);
		
		if(!tarea.getEstado().equals(Estado.PENDIENTE)) {
			throw new TareaNotFoundException("La tarea ya esta completada o en progreso");
	}
	
	tarea.setEstado(Estado.EN_PROCESO);
	return this.tareaRepository.save(tarea);

	}
	// Completar una tarea (solo se puden completar tareas EN_PROGRESO)
	
	public Tarea completarTarea(int idTarea) {
		Tarea tarea = this.findById(idTarea);
		
		if(!tarea.getEstado().equals(Estado.EN_PROCESO)) {
			throw new TareaNotFoundException("La tarea esta completada o pendiente");
		}
		tarea.setEstado(Estado.COMPLETADA);
		return this.tareaRepository.save(tarea);
		
	}
	
	// Obtener las tareas pendientes
	
	public List<Tarea> pendientes(){
		return this.tareaRepository.findByEstado(Estado.PENDIENTE);
		}
	
	// Obtener las tareas en proceso
	
	public List<Tarea> enProceso(){
		return this.tareaRepository.findByEstado(Estado.EN_PROCESO);
		
	}
	// Obtener las tareas completada
	public List<Tarea> completada(){
		return this.tareaRepository.findByEstado(Estado.COMPLETADA);
		
	}
	
}











