package com.formation.eni.gestionPotager.bo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Object of an event
 * 
 * @author tcamus2021
 *
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
	@Id
	@GeneratedValue
	private Integer idActivity;
	private LocalDate date;
	private String evenement;
	@ManyToOne
	private Potager lieu;

	/**
	 * Constructor without id
	 * 
	 * @param date
	 * @param evenement
	 * @param lieu
	 */
	public Activity(LocalDate date, String evenement, Potager lieu) {
		super();
		this.date = date;
		this.evenement = evenement;
		this.lieu = lieu;
	}

}
