package com.formation.eni.gestionPotager.bo;

import javax.persistence.Entity;

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
	private Integer idPlant;
	private String name;
	private String variety;
	
}
