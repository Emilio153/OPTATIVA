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


@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> findById(int id) {
        return pokemonRepository.findById(id);
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

    public Pokemon update(Pokemon pokemon) {
        // Solo permite actualizar tipo1/tipo2
        Optional<Pokemon> existing = pokemonRepository.findById(pokemon.getId());
        if (existing.isPresent()) {
            Pokemon p = existing.get();
            if (pokemon.getTipo1() != p.getTipo2()) {
                p.setTipo1(pokemon.getTipo1());
            }
            if (pokemon.getTipo2() != p.getTipo1()) {
                p.setTipo2(pokemon.getTipo2());
            }
            if (p.getTipo1() == p.getTipo2()) {
                p.setTipo2(Tipo.NINGUNO);
            }
            return pokemonRepository.save(p);
        }
        return null;
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

    public List<Pokemon> findByTipo(Tipo tipo) {
        return pokemonRepository.findByTipo1OrTipo2(tipo, tipo);
    }
}