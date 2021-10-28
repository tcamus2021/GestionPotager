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

	@GetMapping("/field/{idPotager}")
	public String fieldTable(Model model, @PathVariable("idPotager") Integer idPotager) {
		try {
			List<Field> listeFields = manager.getAllField();
			listeFields.removeIf(field -> field.getPotager().getIdPotager() != idPotager);
			model.addAttribute("fields", listeFields);
		} catch (BLLexception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fieldGetAll";
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

//	@GetMapping("/field/delete/{id}")
//	public String deleteTreatment(Model model, @PathVariable Integer idField) {
//		try {
//			List<Field> listeFields = manager.getAllField();
//			listeFields.removeIf(field -> field.getIdField() != idField);
//			manager.deleteField(listeFields.get(0));
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
}
