package com.formation.eni.gestionPotager.dal;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.formation.eni.gestionPotager.bo.Activity;

/**
 * Access to the Activities DATA
 * 
 * @author tcamus2021
 *
 */
public interface ActivityDAO extends CrudRepository<Activity, Integer> {
	
	@Query("FROM Activity a WHERE a.date BEETWEEN ?1 AND ?2")
	List<Activity> findAllForDateInterval(LocalDate firstDate, LocalDate lastDate);
}
