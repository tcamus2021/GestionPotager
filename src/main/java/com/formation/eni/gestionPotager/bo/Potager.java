package com.formation.eni.gestionPotager.bo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Object of the vegetables garden
 * 
 * @author tcamus2021
 *
 */
@Data
@Entity
@NoArgsConstructor
public class Potager {
	@Id
	@GeneratedValue
	private Integer idPotager;
	private String location;
	private String nom;
	private Integer aera;
	private String city;
	private List<Field> fields;

	/**
	 * Constructor without id
	 * 
	 * @param location
	 * @param nom
	 * @param aera
	 * @param city
	 */
	public Potager(String location, String nom, Integer aera, String city) {
		super();
		this.location = location;
		this.nom = nom;
		this.aera = aera;
		this.city = city;
	}

}
