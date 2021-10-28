package com.formation.eni.gestionPotager.ihm.form;

import com.formation.eni.gestionPotager.bo.Potager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PotagerForm {
	private Potager potager;
}
