package com.daw.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.percistence.entities.Pokeball;
import com.daw.percistence.entities.Pokemon;
import com.daw.percistence.entities.Tipo;
import com.daw.percistence.repositories.PokemonRepository;
import com.daw.services.Exceptions.PokemonExceptions;
import com.daw.services.Exceptions.PokemonNotFoundExceptions;


@Service
public class PokemonService {

		// findAll
		// findById
		// save (crear y actualizar)
		// deleteById
		// existsById (nos devuelve true si existe la tarea con esa ID)
	
    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public Pokemon findById(int id) {
    	if(!this.pokemonRepository.existsById(id)){
    		throw new PokemonNotFoundExceptions("El id" + id + "no existe");
    	}
        return pokemonRepository.findById(id).get();
    }

    public Pokemon create(Pokemon pokemon) {
        // Validaciones reglas de negocio
        if (pokemon.getTipo2() == null || pokemon.getTipo1() == pokemon.getTipo2()) {
            pokemon.setTipo2(Tipo.NINGUNO);
        }
        if (pokemon.getCapturado() == null) {
            pokemon.setCapturado(Pokeball.POKEBALL);
        }
        if (pokemon.getFechaCaptura() == null) {
            pokemon.setFechaCaptura(LocalDate.now());
        }
        return pokemonRepository.save(pokemon);
    }

    public Pokemon update(Pokemon pokemon, int id) {
       
    		Pokemon p = pokemonRepository.findById(id).get();
    			
    		if (pokemon.getId() == id) {
          throw new PokemonExceptions(String.format("El ID del body (%d) y el ID del path (%d) no coinciden",pokemon.getId(), id));
        	}
        if (!this.pokemonRepository.existsById(id)) {
            throw new PokemonNotFoundExceptions("El Pokemon con ID" + id + " no existe");
        }
        
        if (pokemon.getTipo2() != p.getTipo1()) {
            p.setTipo2(pokemon.getTipo2());
        }

        if (p.getTipo2() == null) {
            p.setTipo2(Tipo.NINGUNO);
        }
         return pokemonRepository.save(p);
        }
    

    public void delete(int id) {
        pokemonRepository.deleteById(id);
    }

    public Pokemon findByNumeroPokedex(int numero) {
        return pokemonRepository.findByNumeroPokedex(numero);
    }

    public List<Pokemon> findByFechaCapturaBetween(LocalDate start, LocalDate end) {
        return pokemonRepository.findByFechaCapturaBetween(start, end);
    }

    public List<Pokemon> findByTipo1OrTipo2(Tipo tipo) {
        return pokemonRepository.findByTipo1OrTipo2(tipo, tipo);
    }
}