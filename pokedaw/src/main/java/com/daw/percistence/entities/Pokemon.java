package com.daw.percistence.entities;



import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="numero_pokedex")
	private int numeroPokedex;
	private String nombre;
	@Enumerated(EnumType.STRING)
	private Tipo tipo1;
	@Enumerated(EnumType.STRING)
	private Tipo tipo2;
	@Column(name="fecha_captura")
	private LocalDate fechaCaptura;
	@Enumerated(EnumType.STRING)
	private Pokeball capturado;
	
    public Pokemon(int numeroPokedex, String nombre, Tipo tipo1, Tipo tipo2) {
        this.numeroPokedex = numeroPokedex;
        this.nombre = nombre;
        this.tipo1 = tipo1;
        this.tipo2 = (tipo2 == null || tipo1 == tipo2) ? Tipo.NINGUNO : tipo2;
    }
}

