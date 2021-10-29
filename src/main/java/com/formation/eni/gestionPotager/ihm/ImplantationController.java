package com.formation.eni.gestionPotager.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.eni.gestionPotager.bll.BLLexception;
import com.formation.eni.gestionPotager.bll.PotagerManager;
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
		implantation.setField(manager.getFieldById(idField));
		try {
			manager.addImplentationInField(manager.getFieldById(idField), implantation);
		} catch (BLLexception e) {
			model.addAttribute("error",e.getMessage());
			try {
				model.addAttribute("plants", manager.getAllPlant());
			} catch (BLLexception e1) {
				model.addAttribute("error", e1.getMessage());
			}
			return "implantationCreate";
		}
		return "redirect:/field/"+idField;
	}
	
	@GetMapping("/implantation/update/{id}")
	public String goUpdateImplantation(@PathVariable("id") Integer id, Model model, Implantation implantation) {
		try {
			model.addAttribute("plants", manager.getAllPlant());
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
		}
		implantation = manager.getImplantationById(id);
		model.addAttribute("implantation", implantation);
		return "implantationUpdate";
	}
	
	@PostMapping("/implantation/update/{id}")
	public String updateImplantation(@PathVariable("id") Integer id, Model model, Implantation implantation) {
		Implantation toSave = manager.getImplantationById(id);
		toSave.setPlant(implantation.getPlant());
		toSave.setNbPlant(implantation.getNbPlant());
		toSave.setEstablishment(implantation.getEstablishment());
		toSave.setHarvest(implantation.getHarvest());
		try {
			manager.deleteImplantation(toSave);
			manager.addImplentationInField(toSave.getField(), toSave);
		} catch (BLLexception e) {
			model.addAttribute("field", toSave);
			model.addAttribute("error", e.getMessage());
			return "implantationUpdate";
		}
		return "redirect:/field/"+toSave.getField().getIdField();
	}
	
	@GetMapping("/implantation/delete/{id}")
	public String deleteImplantation(@PathVariable Integer id, Model model) {
		Implantation implant = manager.getImplantationById(id);
		manager.deleteImplantation(implant);	
		// TODO pass msg to redirect !!
		model.addAttribute("succes","Implantation delete with succes !");
		return "redirect:/field/"+implant.getField().getIdField();
	}
}
