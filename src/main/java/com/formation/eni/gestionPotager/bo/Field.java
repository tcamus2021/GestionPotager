package com.formation.eni.gestionPotager.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The object for a part of the garden
 * 
 * @author tcamus2021
 *
 */
@Data
@Entity
@NoArgsConstructor
public class Field {
	@Id
	@GeneratedValue
	private Integer idField;
	@ManyToOne
	private Potager potager;
	private Integer aera;
	private GroundType groundType;
	private ExpositionType expositionType;

	/**
	 * Constructor without id
	 * 
	 * @param potager
	 * @param aera
	 * @param groundType
	 * @param expositionType
	 */
	public Field(Potager potager, Integer aera, GroundType groundType, ExpositionType expositionType) {
		super();
		this.potager = potager;
		this.aera = aera;
		this.groundType = groundType;
		this.expositionType = expositionType;
	}

}