package com.formation.eni.gestionPotager.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.eni.gestionPotager.bll.BLLexception;
import com.formation.eni.gestionPotager.bll.PotagerManager;
import com.formation.eni.gestionPotager.bo.Potager;
import com.formation.eni.gestionPotager.ihm.form.PotagerForm;

@Controller
public class PotagerController {


	@Autowired
	PotagerManager manager;
	 
	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/potager")
	public String listPotager(Model model) {
		try {
			model.addAttribute("potagers", manager.getAllPotager());
		} catch (BLLexception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "potagerGetAll";
	}
	 
	@GetMapping("/potager/create")
	public String goCreatePotager(PotagerForm potagerForm, Model model) {
		return "potagerCreate";
	}
	
	@PostMapping("/potager/create")
	public String createPotager(@Valid PotagerForm potagerForm, BindingResult errors, Model model) {
		Potager potager = potagerForm.getPotager();
		try {
			manager.insertPotager(potager);
		} catch (BLLexception e) {
			model.addAttribute("error",e.getMessage());
			return "potagerCreate";
		}
		return "redirect:/potager/"+potager.getIdPotager();
	}
	
	@GetMapping("/potager/{id}")
	public String showPotager(@PathVariable Integer id, PotagerForm potagerForm, Model model) {
		model.addAttribute("potager", manager.getPotagerById(id));
		return "potagerDetails";
	}
	
	
	@GetMapping("/potager/update/{id}")
	public String goUpdatePotager(@PathVariable Integer id, PotagerForm potagerForm, Model model) {
		potagerForm.setPotager(manager.getPotagerById(id));
		return "potagerUpdate";
	}
	
	@PostMapping("/potager/update/{id}")
	public String updatePatager(@Valid PotagerForm potagerForm, BindingResult errors, Model model) {
		Potager potager = potagerForm.getPotager();
		try {
			manager.updatePotager(potager);
		} catch (BLLexception e) {
			model.addAttribute("error",e.getMessage());
			return "potagerUpdate";
		}
		return "redirect:/potager/"+potager.getIdPotager();
	}
	
	@GetMapping("/potager/delete/{id}")
	public String deletePotager(@PathVariable Integer id, Model model) {
		try {
			manager.deletePotager(manager.getPotagerById(id));
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
			try {
				model.addAttribute("potagers", manager.getAllPotager());
			} catch (BLLexception e1) {
				model.addAttribute("error",e.getMessage());
			}
			return "potagerGetAll";
		}
		model.addAttribute("succes","Potager delete with succes !");
		return "potagerGetAll";
	}

}
