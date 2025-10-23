package com.daw.web.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.daw.percistence.entities.Pokemon;
import com.daw.percistence.entities.Tipo;
import com.daw.services.PokemonService;


@RestController
@RequestMapping("/pokemon")
public class PokemonControllers {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<Pokemon>> list() {
        return ResponseEntity.ok(pokemonService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable int id) {
        Optional<Pokemon> pokemon = pokemonService.findById(id);
        return pokemon.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pokemon> create(@RequestBody Pokemon pokemon) {
        return ResponseEntity.ok(pokemonService.create(pokemon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> update(@PathVariable int id, @RequestBody Pokemon pokemon) {
        pokemon.setId(id);
        return ResponseEntity.ok(pokemonService.update(pokemon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        pokemonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Pokemon> findByNumero(@PathVariable int numero) {
        return ResponseEntity.ok(pokemonService.findByNumeroPokedex(numero));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Pokemon>> findByFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(pokemonService.findByFechaCapturaBetween(start, end));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Pokemon>> findByTipo(@PathVariable Tipo tipo) {
        return ResponseEntity.ok(pokemonService.findByTipo(tipo));
    }
}