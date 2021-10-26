package com.formation.eni.gestionPotager.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formation.eni.gestionPotager.bll.BLLexception;
import com.formation.eni.gestionPotager.bll.PotagerManager;
import com.formation.eni.gestionPotager.bo.Plant;

/**
 * It's the controller of the CRUD plant on web service
 * 
 * @author tcamus2021
 *
 */
@RestController
public class WebServicePlant {

	@Autowired
	PotagerManager manager;

	/**
	 * Return all the plants
	 * 
	 * @return
	 */
	@GetMapping("/ws/plant")
	public List<Plant> getAll() {
		try {
			return manager.getAllPlant();
		} catch (BLLexception e) {
			return null;
		}
	}

	/**
	 * To insert a plant via API
	 * 
	 * @param plant
	 * @return
	 */
	@PostMapping("/ws/plant")
	public List<Plant> insert(@RequestBody Plant plant) {
		try {
			manager.insertPlant(plant);
			return manager.getAllPlant();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * To update a plant via API
	 * 
	 * @param plant
	 * @return
	 */
	@PutMapping("/ws/plant")
	public List<Plant> update(@RequestBody Plant plant) {
		try {
			manager.updatePlant(plant);
			return manager.getAllPlant();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * To delete a plant with the id via API
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/ws/plant/{id}")
	public List<Plant> delete(@PathVariable Integer id) {
		try {
			manager.getAllPlant().forEach(plant -> {
				if (plant.getIdPlant() == id) {
					try {
						manager.deletePlant(plant);
					} catch (BLLexception e) {
						e.printStackTrace();
					}
				}
			});
			return manager.getAllPlant();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Create associations whith two named Plants
	 * 
	 * @param first == name of the 1st Plant
	 * @param second  == name of the 2nd Plant
	 * @return == informations message 
	 */
	@GetMapping("/asso/{first}/{second}")
	public String associate(@PathVariable("first") String first, @PathVariable("second") String second) {
		Plant one = manager.getPlantByName(first);
		Plant two = manager.getPlantByName(second);
		if(	 one!= null && two != null) {
			one.associate(two);
			try {
				manager.updatePlant(one);
			} catch (BLLexception e) {
				e.printStackTrace();
				return "Fail can't save first Plant!";
			}
			try {
				manager.updatePlant(two);
			} catch (BLLexception e) {
				e.printStackTrace();
				return "Fail can't save second Plant!";
			}
			return "association Ok !";
		}else {
			return "Fail to associate!";
		}
	}
	
	/**
	 * Get all Plants with associations
	 * 
	 * @return List of all Plants with an association
	 */
	@GetMapping("/asso")
	public List<Plant> showAssociations() {
		return manager.getAllAssociations();
	}
}
