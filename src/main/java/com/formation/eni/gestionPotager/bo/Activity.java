package com.formation.eni.gestionPotager.bo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private Lieu lieu;
}
