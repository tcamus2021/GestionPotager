package com.formation.eni.gestionPotager.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.formation.eni.gestionPotager.bll.BLLexception;
import com.formation.eni.gestionPotager.bll.PotagerManager;
import com.formation.eni.gestionPotager.bo.Potager;

@RestController
public class WebServicePotager {
	@Autowired
	PotagerManager manager;

	@GetMapping("ws/potager")
	public List<Potager> getAllPotager() {
		try {
			return manager.getAllPotager();
		} catch (BLLexception e) {
			e.printStackTrace();
			System.err.println("nothing to display !");
			return null;
		}
	}
	
	@GetMapping("ws/potager/{id}")
	public Potager getById(@PathVariable("id") Integer id) {
		return manager.getPotagerById(id);
	}
}
