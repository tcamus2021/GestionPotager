package com.formation.eni.gestionPotager.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.formation.eni.gestionPotager.bo.Implantation;

/**
 * Access to the implantation DATA
 * 
 * @author tcamus2021
 *
 */
public interface ImplantationDAO extends CrudRepository<Implantation, Integer> {

	@Query()
	List<Implantation> findAllWherePlantIs(String name);

}
