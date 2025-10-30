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

    public Pokemon cambiarTipo(int id, String tipo1,String tipo2) {
       
    		try {
    			Tipo t1 = Tipo.valueOf(tipo1.toUpperCase());
    			Tipo t2;
    			if(tipo2 != null) {
    				t2 = Tipo.valueOf(tipo2.toUpperCase());
    			}else {
    				t2 = Tipo.NINGUNO;
    			}
    			if(t1.equals(t2)) {
    				throw new PokemonExceptions("Los tipos no pueden coincidir");
    			}
    			Pokemon pokemonBD = this.findById(id);
    			pokemonBD.setTipo1(t1);
    			pokemonBD.setTipo2(t2);
    			return this.pokemonRepository.save(pokemonBD);
    		}catch(IllegalArgumentException ex) {
    			throw new PokemonExceptions("El tipo indicado no es valido");
    		}
    
    		
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