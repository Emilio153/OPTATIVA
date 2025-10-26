package com.daw.web.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.daw.percistence.entities.Pokemon;
import com.daw.percistence.entities.Tipo;
import com.daw.services.PokemonService;
import com.daw.services.Exceptions.PokemonExceptions;
import com.daw.services.Exceptions.PokemonNotFoundExceptions;


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
    public ResponseEntity<?> findById(@PathVariable int id) {
    	try {
    		return ResponseEntity.ok(this.pokemonService.findById(id));
    	}catch (PokemonNotFoundExceptions ex) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    	}
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pokemon pokemon) {
    		try {
    			return ResponseEntity.status(HttpStatus.CREATED).body(this.pokemonService.create(pokemon));
    		}catch(PokemonExceptions ex){
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    		}
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Pokemon pokemon) {
        try {
        	return ResponseEntity.ok(this.pokemonService.update(pokemon, id));
        }catch(PokemonExceptions ex) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
    	try {
    		 this.pokemonService.delete(id);
    		 return ResponseEntity.ok().build();
    	}catch(PokemonNotFoundExceptions ex){
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    	}
       
       
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<?> findByNumeroPokedex(@PathVariable int numero) {
        return ResponseEntity.ok(pokemonService.findByNumeroPokedex(numero));
    }

    @GetMapping("/fecha")
    public ResponseEntity<?> findByFechaCapturaBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(pokemonService.findByFechaCapturaBetween(start, end));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> findByTipo1OrTipo2(@PathVariable Tipo tipo) {
        return ResponseEntity.ok(pokemonService.findByTipo1OrTipo2(tipo));
    }
}