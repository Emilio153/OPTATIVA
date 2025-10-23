package com.daw.percistence.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.percistence.entities.Pokemon;
import com.daw.percistence.entities.Tipo;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
    Pokemon findByNumeroPokedex(int numeroPokedex);
    List<Pokemon> findByFechaCapturaBetween(LocalDate start, LocalDate end);
    List<Pokemon> findByTipo1OrTipo2(Tipo tipo1, Tipo tipo2);
}