package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.Tarea;
import com.daw.service.TareaService;
import com.daw.service.exceptions.TareaException;
import com.daw.service.exceptions.TareaNotFoundException;

@RestController
@RequestMapping("/tareas")
public class TareaController {
	@Autowired
	private TareaService tareaService;
	
	// Obtener todas las tareas
	@GetMapping
	public ResponseEntity<List<Tarea>> list(){
		return ResponseEntity.ok(this.tareaService.findAll());
		
	}
	// Obtener una tarea por ID
	@GetMapping("/{idTarea}")
	public ResponseEntity<?> findById(@PathVariable int idTarea){
		try {
			return ResponseEntity.ok(this.tareaService.findById(idTarea));
		}
		catch (TareaNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}
		
	}
	// Crear una nueva tarea
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Tarea tarea){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.tareaService.create(tarea));
 		}
		catch(TareaException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			
		}
	}
	// Actualizar una tarea existente
	@PutMapping("/{idTarea}")
	public ResponseEntity<?> update(@PathVariable int idTarea, @RequestBody Tarea tarea){
		try {
			return ResponseEntity.ok(this.tareaService.update(tarea, idTarea));
		}
		catch(TareaNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
			
		}
		catch(TareaException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	// Eliminar una tarea por ID
	@DeleteMapping("/{idTarea}")
	public ResponseEntity<?> delete(@PathVariable int idTarea){
		try {
			this.tareaService.delete(idTarea);
			return ResponseEntity.ok().build();
		}
		catch (TareaNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	@PutMapping("/{idTarea}/completar")
	public ResponseEntity<?> CompletarTarea(@PathVariable int idTarea) {

		try {
			return ResponseEntity.ok(this.tareaService.completarTarea(idTarea));

		} catch (TareaNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		} catch (TareaException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}

	}
	// Obtener las tareas pendientes.
	@GetMapping("/pendientes")
	public ResponseEntity<List<Tarea>> pendientes(){
		return ResponseEntity.ok(this.tareaService.pendientes());
	}
	// Obtener las tareas en proceso.
	@GetMapping("/enProceso")
	public ResponseEntity<List<Tarea>> enProceso(){
		return ResponseEntity.ok(this.tareaService.enProceso());
		
	}
	// Obtener las tareas completadas.
	@GetMapping("/completada")
	public ResponseEntity<List<Tarea>> completada(){
		return ResponseEntity.ok(this.tareaService.completada());
	}
	
	@GetMapping("/noVencidas")
	public ResponseEntity<?> noVencidas() {
		return ResponseEntity.ok(this.tareaService.noVencidas());
	}

	@GetMapping("/vencidas")
	public ResponseEntity<?> vencidas() {
		return ResponseEntity.ok(this.tareaService.vencidas());
	}

	@GetMapping("/buscar")
	public List<Tarea> tareasPorTitulo(@RequestParam String titulo) {
		return tareaService.tareasPorTitulo(titulo);
	}
	

}
