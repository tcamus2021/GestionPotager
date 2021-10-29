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

@Controller
public class FieldController {

	@Autowired
	PotagerManager manager;

	@GetMapping("/field")
	public String fieldGetAll(Model model) {
		try {
			model.addAttribute("fields", manager.getAllField());
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "fieldGetAll";
	}
	
	@GetMapping("/field/create/{idPotager}")
	public String goCreateField(@PathVariable Integer idPotager, Model model, Field field) {
		model.addAttribute("potager", manager.getPotagerById(idPotager));
		return "fieldCreate";
	}
	
	@PostMapping("/field/create/{idPotager}")
	public String createField(@PathVariable Integer idPotager, Model model, Field field) {
		field.setPotager(manager.getPotagerById(idPotager));
		System.err.println(idPotager);
		try {
			manager.insertField(field);
		} catch (BLLexception e) {
			model.addAttribute("error",e.getMessage());
			model.addAttribute("potager", manager.getPotagerById(idPotager));
			return "fieldCreate";
		}
		return "redirect:/field/"+field.getIdField();
	}
	
	@GetMapping("/field/{id}")
	public String fieldDetails(Model model, @PathVariable("id") Field field) {
		model.addAttribute("field", field);
		return "fieldDetails";
	}
	
	@GetMapping("/field/update/{id}")
	public String goUpdate(@PathVariable("id") Integer id, Model model, Field field) {
		field = manager.getFieldById(id);
		model.addAttribute("field", field);
		return "fieldUpdate";
	}
	
	@PostMapping("/field/update/{id}")
	public String update(@PathVariable("id") Integer idField, Model model, Field field) {
		Field toSave = manager.getFieldById(idField);
		toSave.setAera(field.getAera());
		toSave.setGroundType(field.getGroundType());
		toSave.setExpositionType(field.getExpositionType());
		try {
			manager.insertField(toSave);
		} catch (BLLexception e) {
			model.addAttribute("field", toSave);
			model.addAttribute("error", e.getMessage());
			return "fieldUpdate";
		}
		return "redirect:/field/"+toSave.getIdField();
	}
	
	@GetMapping("/field/delete/{id}")
	public String deleteField(@PathVariable Integer id, Model model) {
		try {
			manager.deleteField(manager.getFieldById(id));
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("field",manager.getFieldById(id));
			return "fieldDetails";
		}
		// TODO pass msg to redirect !!
		model.addAttribute("succes","Field delete with succes !");
		return "redirect:/potager";
	}
}
