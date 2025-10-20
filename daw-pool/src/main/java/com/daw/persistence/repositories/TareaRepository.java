package com.daw.persistence.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Estado;
import com.daw.persistence.entities.Tarea;
import java.util.List;


public interface TareaRepository extends ListCrudRepository <Tarea, Integer> {
	
	List<Tarea> findByEstado(Estado estado);

}
