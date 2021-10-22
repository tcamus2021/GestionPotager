package com.formation.eni.gestionPotager.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.formation.eni.gestionPotager.bo.Implantation;

/**
 * Access to the implantation DATA
 * 
 * @author tcamus2021
 *
 */
public interface ImplantationDAO extends CrudRepository<Implantation, Integer> {
	
	@Query("SELECT i FROM Implantation i WHERE i.plant.name = :name AND "
			+ "(i.plant.variety IS NULL OR i.plant.variety = :variety)")
	List<Implantation> findAllWherePlantIs(@Param("name") String name, @Param("variety") String variety);
}
