package com.daw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.percistence.entities.Pokemon;
import com.daw.percistence.repositories.PokemonRepository;

import java.util.List;
@Service
public class PokemonService {

	private PokemonRepository pokemonRepository;
	// Obtener todos los pokemon.
		public List<Pokemon> findAll(){
			return this.pokemonRepository.findAll();
		}
		public Pokemon findById (int id) {
			return this.pokemonRepository.findById(id).get(id);
}
}
