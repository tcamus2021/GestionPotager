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

/**
 * Controller of the field
 * 
 * @author tcamus2021
 *
 */
@Controller
public class FieldController {

	@Autowired
	PotagerManager manager;

	/**
	 * The method for the field liste
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/field")
	public String fieldGetAll(Model model) {
		try {
			model.addAttribute("fields", manager.getAllField());
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "fieldGetAll";
	}

	/**
	 * The form to create field
	 * 
	 * @param idPotager
	 * @param model
	 * @param field
	 * @return
	 */
	@GetMapping("/field/create/{idPotager}")
	public String goCreateField(@PathVariable Integer idPotager, Model model, Field field) {
		model.addAttribute("potager", manager.getPotagerById(idPotager));
		return "fieldCreate";
	}

	/**
	 * Treatment of the field creat
	 * 
	 * @param idPotager
	 * @param model
	 * @param field
	 * @return
	 */
	@PostMapping("/field/create/{idPotager}")
	public String createField(@PathVariable Integer idPotager, Model model, Field field) {
		field.setPotager(manager.getPotagerById(idPotager));
		try {
			manager.insertField(field);
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
			return "fieldCreate";
		}
		return "redirect:/field/" + field.getIdField();
	}

	/**
	 * The details of a field
	 * 
	 * @param model
	 * @param field
	 * @return
	 */
	@GetMapping("/field/{id}")
	public String fieldDetails(Model model, @PathVariable("id") Field field) {
		model.addAttribute("field", field);
		return "fieldDetails";
	}

	/**
	 * The form to update field
	 * 
	 * @param id
	 * @param model
	 * @param field
	 * @return
	 */
	@GetMapping("/field/update/{id}")
	public String goUpdate(@PathVariable("id") Integer id, Model model, Field field) {
		field = manager.getFieldById(id);
		model.addAttribute("field", field);
		return "fieldUpdate";
	}

	/**
	 * The treatment of the field
	 * 
	 * @param idField
	 * @param model
	 * @param field
	 * @return
	 */
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
		return "redirect:/field/" + toSave.getIdField();
	}

	/**
	 * The delete of the field
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/field/delete/{id}")
	public String deleteField(@PathVariable Integer id, Model model) {
		try {
			manager.deleteField(manager.getFieldById(id));
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("field", manager.getFieldById(id));
			return "fieldDetails";
		}
		// TODO pass msg to redirect !!
		model.addAttribute("succes", "Field delete with succes !");
		return "redirect:/potager";
	}
}
