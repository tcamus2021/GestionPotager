package com.formation.eni.gestionPotager.dal;

import org.springframework.data.repository.CrudRepository;

import com.formation.eni.gestionPotager.bo.Potager;

/**
 * Access to the potager DATA
 * 
 * @author tcamus2021
 *
 */
public interface PotagerDAO extends CrudRepository<Potager, Integer> {

}
