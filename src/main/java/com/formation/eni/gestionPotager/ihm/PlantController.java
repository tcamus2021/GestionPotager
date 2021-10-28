package com.formation.eni.gestionPotager.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.eni.gestionPotager.bll.BLLexception;
import com.formation.eni.gestionPotager.bll.PotagerManager;
import com.formation.eni.gestionPotager.bo.Plant;

@Controller
public class PlantController {
	
	@Autowired
	PotagerManager manager;

	@GetMapping("/plant")
	public String plantGetAll(Model model) {
		try {
			model.addAttribute("plants", manager.getAllPlant());
		} catch (BLLexception e) {
			e.printStackTrace();
		}
		return "plantGetAll";
	}
	
	@GetMapping("/plant/update/{id}")
	public String update(Model model, @PathVariable("id") Plant plant) {
		model.addAttribute("plant", plant);
		return "plantUpdate";
	}
	
	@PostMapping("/plant/update/{id}")
	public String delete(Model model, @Valid Plant plant, @PathVariable("id") Integer id) {
		try {
			plant.setIdPlant(id);
			manager.updatePlant(plant);
		} catch (BLLexception e) {
			e.printStackTrace();
		}
		return "redirect:/plant";
	}
	
	@GetMapping("/plant/delete/{id}")
	public String delete(@PathVariable("id") Plant plant) {
		try {
			manager.deletePlant(plant);
		} catch (BLLexception e) {
			e.printStackTrace();
		}
		return "redirect:/plant";
	}
	
	@GetMapping("plant/create")
	public String create(Plant plant, Model model) {
		return "plantCreate";
	}
	
	@PostMapping("/plant/create")
	public String createTreatment(@Valid Plant plant) {
		try {
			manager.insertPlant(plant);
		} catch (BLLexception e) {
			e.printStackTrace();
		}
		return "redirect:/plant/";
	}
	
}
