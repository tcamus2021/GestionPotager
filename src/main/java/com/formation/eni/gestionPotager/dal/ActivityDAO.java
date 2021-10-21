package com.formation.eni.gestionPotager.dal;

import org.springframework.data.repository.CrudRepository;

import com.formation.eni.gestionPotager.bo.Activity;

/**
 * Access to the Activities DATA
 * 
 * @author tcamus2021
 *
 */
public interface ActivityDAO extends CrudRepository<Activity, Integer> {

}
