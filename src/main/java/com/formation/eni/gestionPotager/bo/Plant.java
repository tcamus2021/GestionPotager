package com.formation.eni.gestionPotager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class Plant {
	@Id
	@GeneratedValue
	private Integer idPlant;
	private String name;
	private PlantType plantType;
	private String variety;
	private Integer aera; // (cm²)

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

}
