package com.formation.eni.gestionPotager.bo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
public class Implantation {
	@Id
	@GeneratedValue
	private Integer idImplentation;
	@OneToOne
	private Plant plant;
	private Integer nbPlant;
	private LocalDate establishment;
	private LocalDate harvest;
	@ManyToOne
	private Field field;

	/**
	 * Constructor with id
	 * 
	 * @param plant
	 * @param nbPlant
	 * @param establishment
	 * @param harvest
	 * @param plant
	 */
	public Implantation(Plant plant, Integer nbPlant, LocalDate establishment, LocalDate harvest) {
		this.plant = plant;
		this.nbPlant = nbPlant;
		this.establishment = establishment;
		this.harvest = harvest;
	}
}
