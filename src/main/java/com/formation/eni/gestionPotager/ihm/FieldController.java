package com.formation.eni.gestionPotager.ihm;

import java.util.List;

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
			e.printStackTrace();
		}
		return "fieldGetAll";
	}
	
	@GetMapping("/field/{id}")
	public String fieldDetails(Model model, @PathVariable("id") Field field) {
		model.addAttribute("field", field);
		return "fieldDetails";
	}

	@GetMapping("/field/update/{id}")
	public String update(Model model, @PathVariable("id") Field field) {
		model.addAttribute("field", field);
		return "fieldUpdate";
	}

	@GetMapping("/field/update/treatment")
	public String goFieldTable() {
		return "redirect:/";
	}

	@PostMapping("/field/update/treatment")
	public String updateTreatment(Model model, Field field) {
		model.addAttribute("field", field);
		return "fieldUpdate";
	}

	@GetMapping("/field/delete/{id}")
	public String deleteTreatment(Model model, @PathVariable Integer id) {
		String url = "redirect:/";
		try {
			List<Field> listeFields = manager.getAllField();
			listeFields.removeIf(field -> field.getIdField() != id);
			if(listeFields.size() != 0) {
				url = "redirect:/field/" + listeFields.get(0).getPotager().getIdPotager();
				manager.deleteField(listeFields.get(0));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return url;
	}
	
	@GetMapping("/field/add")
	public String addField(Model model, Field field) {
		return "fieldAdd";
	}
	
	@GetMapping("/field/create/{idPotager}")
	public String goCreateField(@PathVariable Integer idPotager, Model model, Field field) {
		model.addAttribute("potager", manager.getPotagerById(idPotager));
		return "fieldCreate";
	}
	
	@PostMapping("/field/create/{idPotager}")
	public String createField(@PathVariable Integer idPotager, Model model, Field field) {
		field.setPotager(manager.getPotagerById(idPotager));
		try {
			manager.insertField(field);
		} catch (BLLexception e) {
			model.addAttribute("error",e.getMessage());
			return "fieldCreate";
		}
		return "redirect:/field/"+field.getIdField();
	}
}
