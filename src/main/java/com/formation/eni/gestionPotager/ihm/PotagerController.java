package com.formation.eni.gestionPotager.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.formation.eni.gestionPotager.bll.BLLexception;
import com.formation.eni.gestionPotager.bll.PotagerManager;

@Controller
public class PotagerController {


	@Autowired
	PotagerManager manager;
	 
	@GetMapping("/")
	public String home(Model model) {
		return "redirect:/potager";
	}
	
	@GetMapping("/potager")
	public String listPotager(Model model) {
//		model.addAttribute("msg","Bienvenu ! vous-pouvez démarer un calcul !");
		try {
			model.addAttribute("potagers", manager.getAllPotager());
		} catch (BLLexception e) {
			model.addAttribute("error",e.getMessage());
		}
		return "potager";
	}
	 
//	@GetMapping("/potager/add")
//	public String newPotager() {
//		return "redirect:/";
//	}
//	
//	@PostMapping("/potager/add")
//	public String calc(@Valid CalculatriceForm calculatriceForm, BindingResult errors, Model model) {
//		Calcule calc = new Calcule();
//		try {
//			calc = Calcule.builder()
//					.numA(calculatriceForm.getNumA())
//					.operateur(calculatriceForm.getOperateur())
//					.numB(calculatriceForm.getNumB())
//					.res(manager.calculer(calculatriceForm)).build();
//		} catch (Exception e) {
//			model.addAttribute("msg",e.getMessage());
//		}
//		if(calc.getRes() != null) {
//			manager.addCalcule(calc);
//			model.addAttribute("msg","Resultat du calcul sauvegarder ! : res = "+calc.getRes());
//		}
//		model.addAttribute("resultats", manager.getAllCalcules());
//		return "home";
//	}
}
