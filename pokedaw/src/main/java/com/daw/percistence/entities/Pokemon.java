package com.daw.percistence.entities;

import java.time.LocalDate;

import com.daw.persistence.entities.Estado;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pokemon")

public class Pokemon {
	private int id;
	
	private int numero_pokedex;
	private String nombre;
	private Tipo tipo1;
	private Tipo tipo2;
	private LocalDate fechaCaptura;
	private Pokeball capturado;
}
