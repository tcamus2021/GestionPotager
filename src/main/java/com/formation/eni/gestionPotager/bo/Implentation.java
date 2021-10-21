package com.formation.eni.gestionPotager.bo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	@ManyToMany
	private List<Plant> plants;
	@ManyToOne
	private Field field;

	/**
	 * Constructor with id
	 * 
	 * @param nbPlant
	 * @param establishment
	 * @param harvest
	 * @param plant
	 */
	public Implentation(Integer nbPlant, LocalDate establishment, LocalDate harvest) {
		this.nbPlant = nbPlant;
		this.establishment = establishment;
		this.harvest = harvest;
	}

	/**
	 * To add a plant in the garden
	 * 
	 * @param plant
	 */
	public void addPlant(Plant plant) {
		this.plants.add(plant);
	}

}
