package com.formation.eni.gestionPotager.dal;

import org.springframework.data.repository.CrudRepository;

import com.formation.eni.gestionPotager.bo.Implentation;

/**
 * Access to the implentation DATA
 * 
 * @author tcamus2021
 *
 */
public interface ImplentationDAO extends CrudRepository<Implentation, Integer> {

}
