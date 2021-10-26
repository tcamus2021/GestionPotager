package com.formation.eni.gestionPotager.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.formation.eni.gestionPotager.bo.Plant;



/**
 * Access to the plant DATA
 * 
 * @author tcamus2021
 *
 */
public interface PlantDAO extends CrudRepository<Plant, Integer> {
	
	List<Plant> findByNameAndVariety(String name, String variety);
	
	Plant findByName(String first);

	@Query("SELECT p FROM Plant p JOIN p.associations pa WHERE pa IS NOT NULL")
	List<Plant> findAllWhereAssociations();
}
