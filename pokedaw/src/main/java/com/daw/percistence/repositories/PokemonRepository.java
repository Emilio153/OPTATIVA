package com.daw.percistence.repositories;

import java.util.List;

import com.daw.percistence.entities.Pokemon;

public interface PokemonRepository extends List {

	List<Pokemon> findAll();

	List<Pokemon> findById(int id);

}
