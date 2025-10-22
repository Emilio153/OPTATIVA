package com.daw.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.percistence.entities.Pokemon;
import com.daw.services.PokemonService;

@RestController
@RequestMapping("/pokemon")
public class PokemonControllers {
	
@Autowired
private PokemonService pokemonService;

@GetMapping
public ResponseEntity<Object> list(){
	return  ResponseEntity.ok(this.pokemonService.findAll());

}
}
