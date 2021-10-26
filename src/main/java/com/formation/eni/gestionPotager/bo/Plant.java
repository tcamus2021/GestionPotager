package com.formation.eni.gestionPotager.bo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Object for the plant
 * 
 * @author tcamus2021
 *
 */
@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idPlant")
public class Plant {
	@Id
	@GeneratedValue
	private Integer idPlant;
	private String name;
	private PlantType plantType;
	private String variety;
	private Integer aera; // (square centimeter)
	@ManyToMany
	@JsonManagedReference
	private List<Plant> associations;

	/**
	 * Constructor without id
	 * 
	 * @param name
	 * @param plantType
	 * @param variety
	 * @param aera 
	 */
	public Plant(String name, PlantType plantType, String variety, Integer aera) {
		super();
		this.name = name;
		this.plantType = plantType;
		this.variety = variety;
		this.aera = aera;
	}

	public void associate(Plant plant) {
		this.associations.add(plant);
		plant.associations.add(this);
	}
}
