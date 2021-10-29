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

/**
 * Controller for the potager
 * 
 * @author tcamus2021
 *
 */
@Controller
public class PotagerController {

	@Autowired
	PotagerManager manager;

	/**
	 * Home url
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}

	/**
	 * List of all the potager
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/potager")
	public String potagerGetAll(Model model) {
		try {
			model.addAttribute("potagers", manager.getAllPotager());
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "potagerGetAll";
	}

	/**
	 * Creation of potager
	 * 
	 * @param potagerForm
	 * @param model
	 * @return
	 */
	@GetMapping("/potager/create")
	public String goCreatePotager(PotagerForm potagerForm, Model model) {
		return "potagerCreate";
	}

	/**
	 * Treatment of creation potager
	 * 
	 * @param potagerForm
	 * @param errors
	 * @param model
	 * @return
	 */
	@PostMapping("/potager/create")
	public String createPotager(@Valid PotagerForm potagerForm, BindingResult errors, Model model) {
		Potager potager = potagerForm.getPotager();
		try {
			manager.insertPotager(potager);
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
			return "potagerCreate";
		}
		return "redirect:/potager/" + potager.getIdPotager();
	}

	/**
	 * Details of a potager
	 * 
	 * @param id
	 * @param potagerForm
	 * @param model
	 * @return
	 */
	@GetMapping("/potager/{id}")
	public String showPotager(@PathVariable Integer id, PotagerForm potagerForm, Model model) {
		model.addAttribute("potager", manager.getPotagerById(id));
		return "potagerDetails";
	}

	/**
	 * Go to the form for update
	 * 
	 * @param id
	 * @param potagerForm
	 * @param model
	 * @return
	 */
	@GetMapping("/potager/update/{id}")
	public String goUpdatePotager(@PathVariable Integer id, PotagerForm potagerForm, Model model) {
		potagerForm.setPotager(manager.getPotagerById(id));
		return "potagerUpdate";
	}

	/**
	 * Update treatment
	 * 
	 * @param id
	 * @param potagerForm
	 * @param errors
	 * @param model
	 * @return
	 */
	@PostMapping("/potager/update/{id}")
	public String updatePatager(@PathVariable Integer id, @Valid PotagerForm potagerForm, BindingResult errors,
			Model model) {
		Potager potager = potagerForm.getPotager();
		potager.setIdPotager(id);
		try {
			manager.updatePotager(potager);
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
			return "potagerUpdate";
		}
		return "redirect:/potager/" + potager.getIdPotager();
	}

	/**
	 * Treatment to delete
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/potager/delete/{id}")
	public String deletePotager(@PathVariable Integer id, Model model) {
		try {
			manager.deletePotager(manager.getPotagerById(id));
		} catch (BLLexception e) {
			model.addAttribute("error", e.getMessage());
			try {
				model.addAttribute("potagers", manager.getAllPotager());
			} catch (BLLexception e1) {
				model.addAttribute("error", e1.getMessage());
			}
			return "potagerGetAll";
		}
		model.addAttribute("succes", "Potager delete with succes !");
		return "potagerGetAll";
	}

}
