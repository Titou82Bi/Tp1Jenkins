package com.inti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table @Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int idHotel;
	@Column(nullable = false, unique = true )
	private String nom;
	private int nbEtoile;
	public Hotel(String nom, int nbEtoile) {
		super();
		this.nom = nom;
		this.nbEtoile = nbEtoile;
	}
	
	
}
