package com.formation.eni.gestionPotager.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "idPotager")
public class Potager implements Lieu {
	@Id
	@GeneratedValue
	private Integer idPotager;
	private String location;
	private String nom;
	private Integer aera; // (square meter)
	private String city;
	@OneToMany(mappedBy = "potager")
	@JsonManagedReference
	private List<Field> fields = new ArrayList<Field>();

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

	/**
	 * To add a field in the garden
	 * 
	 * @param field
	 */
	public void addField(Field field) {
		field.setPotager(this);
		this.fields.add(field);
	}

}
