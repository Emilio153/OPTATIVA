package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Estado;
import com.daw.persistence.entities.Tarea;
import java.time.LocalDate;

public interface TareaRepository extends ListCrudRepository<Tarea, Integer> {
	


	//	Obtener las tareas completadas.
	List<Tarea> findByEstado(Estado estado);

	//	Obtener las tareas que no están vencidas
	List<Tarea> findByFechaVencimientoAfter(LocalDate fechaVencimiento);
	
	//	Obtener las tareas vencidas.
	List<Tarea> findByFechaVencimientoBefore(LocalDate fechaVencimiento);

	// Obtener por título
	List<Tarea> findByTituloContainingIgnoreCase(String titulo);

}

