package com.formation.eni.gestionPotager.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formation.eni.gestionPotager.bll.PotagerManager;
import com.formation.eni.gestionPotager.bo.Implantation;

@Controller
public class ImplantationController {

	@Autowired
	PotagerManager manager;
	
	@GetMapping("/implantation/create/{idField}")
	public String goCreateImplantation(@PathVariable("idField") Integer idField, Implantation implantation, Model model) {
		//TODO is placeholder !
		model.addAttribute("fieldId", idField);
		return "implantationCreate";
	}
}
