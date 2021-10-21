package com.formation.eni.gestionPotager.dal;

import org.springframework.data.repository.CrudRepository;

import com.formation.eni.gestionPotager.bo.Field;

/**
 * Access to the field DATA
 * 
 * @author tcamus2021
 *
 */
public interface FieldDAO extends CrudRepository<Field, Integer> {

}
