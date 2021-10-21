package com.formation.eni.gestionPotager.bo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objects for the implentation of a plant
 * 
 * @author tcamus2021
 *
 */
@Data
@Entity
@NoArgsConstructor
public class Implentation {
	@Id
	@GeneratedValue
	private Integer idImplentation;
	private Integer nbPlant;
	private LocalDate establishment;
	private LocalDate harvest;
	private Plant plant;

	/**
	 * Constructor with id
	 * 
	 * @param nbPlant
	 * @param establishment
	 * @param harvest
	 * @param plant
	 */
	public Implentation(Integer nbPlant, LocalDate establishment, LocalDate harvest, Plant plant) {
		this.nbPlant = nbPlant;
		this.establishment = establishment;
		this.harvest = harvest;
		this.plant = plant;
	}

}
