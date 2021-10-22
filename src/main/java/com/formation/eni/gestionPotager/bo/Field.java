package com.formation.eni.gestionPotager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private Integer aera; // (square meter)
	private GroundType groundType;
	private ExpositionType expositionType;
	@OneToMany(mappedBy = "field")
	private List<Implantation> implantations = new ArrayList<Implantation>();

	/**
	 * Constructore without id and list
	 * 
	 * @param potager
	 * @param aera
	 * @param groundType
	 * @param expositionType
	 */
	public Field(Integer aera, GroundType groundType, ExpositionType expositionType) {
		this.aera = aera;
		this.groundType = groundType;
		this.expositionType = expositionType;
	}

	/**
	 * To add an implementation in the field
	 * 
	 * @param implantation
	 */
	public void addImplentation(Implantation implantation) {
		implantation.setField(this);
		this.implantations.add(implantation);
	}

}
