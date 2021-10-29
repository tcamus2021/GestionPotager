package com.formation.eni.gestionPotager.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	 * @throws WSexception
	 */
	@GetMapping("/ws/plant")
	public List<Plant> getAll() throws WSexception {
		try {
			return manager.getAllPlant();
		} catch (BLLexception e) {
			throw new WSexception(e.getMessage());
		}
	}

	/**
	 * To insert a plant via API
	 * 
	 * @param plant
	 * @return
	 * @throws WSexception
	 */
	@PostMapping("/ws/plant")
	public List<Plant> insert(@RequestBody Plant plant) throws WSexception {
		try {
			manager.insertPlant(plant);
			return manager.getAllPlant();
		} catch (Exception e) {
			throw new WSexception(e.getMessage());
		}
	}

	/**
	 * To update a plant via API
	 * 
	 * @param plant
	 * @return
	 * @throws WSexception
	 */
	@PutMapping("/ws/plant")
	public List<Plant> update(@RequestBody Plant plant) throws WSexception {
		try {
			manager.updatePlantWS(plant);
			return manager.getAllPlant();
		} catch (Exception e) {
			throw new WSexception(e.getMessage());
		}
	}

	/**
	 * To delete a plant with the id via API
	 * 
	 * @param id
	 * @return
	 * @throws WSexception
	 */
	@DeleteMapping("/ws/plant/{id}")
	public List<Plant> delete(@PathVariable Integer id) throws WSexception {
		try {
			for (Plant plant : manager.getAllPlant()) {
				if (plant.getIdPlant() == id) {
					manager.deletePlant(plant);
				}
			}
			return manager.getAllPlant();
		} catch (Exception e) {
			throw new WSexception(e.getMessage());
		}
	}

	/**
	 * Create associations whith two named Plants
	 * 
	 * @param first  == name of the 1st Plant
	 * @param second == name of the 2nd Plant
	 * @return == informations message
	 * @throws WSexception 
	 */
	@GetMapping("/asso/{first}/{second}")
	public String associate(@PathVariable("first") String first, @PathVariable("second") String second) throws WSexception {
		Plant one = manager.getPlantByName(first);
		Plant two = manager.getPlantByName(second);
		if (one != null && two != null) {
			one.associate(two);
			try {
				manager.updatePlantWS(one);
			} catch (BLLexception e) {
				throw new WSexception("Fail can't save first Plant!");
			}
			try {
				manager.updatePlantWS(two);
			} catch (BLLexception e) {
				throw new WSexception("Fail can't save second Plant!");
			}
			return "Association Ok!";
		} else {
			throw new WSexception("Fail to associate!");
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

	/**
	 * To handle the web service exception
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ WSexception.class })
	public String handler(WSexception e) {
		return e.getMessage();
	}
}
