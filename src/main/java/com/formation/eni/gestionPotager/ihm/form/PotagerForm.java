package com.formation.eni.gestionPotager.ihm.form;

import com.formation.eni.gestionPotager.bo.Potager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class for the form of potager
 * 
 * @author tcamus2021
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PotagerForm {
	private Potager potager;
}
