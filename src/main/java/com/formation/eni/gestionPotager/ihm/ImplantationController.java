package com.formation.eni.gestionPotager.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.eni.gestionPotager.bll.BLLexception;
import com.formation.eni.gestionPotager.bll.PotagerManager;
import com.formation.eni.gestionPotager.bo.Field;
import com.formation.eni.gestionPotager.bo.Implantation;

@Controller
public class ImplantationController {

	@Autowired
	PotagerManager manager;
	
	@GetMapping("/implantation/create/{idField}")
	public String goCreateImplantation(@PathVariable("idField") Integer idField, Implantation implantation, Model model) {
		try {
			model.addAttribute("plants", manager.getAllPlant());
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("fieldId", idField);
		return "implantationCreate";
	}
	
	@PostMapping("/implantation/create/{idField}")
	public String createImplantation(@PathVariable Integer idField, Implantation implantation, Model model) {
		System.err.println(manager.getFieldById(idField));
		System.err.println(implantation);
		try {
			manager.addImplentationInField(manager.getFieldById(idField), implantation);
		} catch (BLLexception e) {
			model.addAttribute("error",e.getMessage());
			return "implantationCreate";
		}
		return "redirect:/field/"+idField;
	}
}
