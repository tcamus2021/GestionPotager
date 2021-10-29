package com.formation.eni.gestionPotager.bll;

import java.util.List;

import com.formation.eni.gestionPotager.bo.Activity;
import com.formation.eni.gestionPotager.bo.Field;
import com.formation.eni.gestionPotager.bo.Implantation;
import com.formation.eni.gestionPotager.bo.Plant;
import com.formation.eni.gestionPotager.bo.Potager;

public interface PotagerManager {
	/**
	 * To insert a garden in the application
	 * 
	 * @param potager
	 * @throws BLLexception
	 */
	public void insertPotager(Potager potager) throws BLLexception;

	/**
	 * To update a garden
	 * 
	 * @param potager
	 * @throws BLLexception
	 */
	public void updatePotager(Potager potager) throws BLLexception;

	/**
	 * To delete a garden
	 * 
	 * @param potager
	 * @throws BLLexception
	 */
	public void deletePotager(Potager potager) throws BLLexception;

	/**
	 * To get th elist of all garden
	 * 
	 * @return
	 * @throws BLLexception
	 */
	public List<Potager> getAllPotager() throws BLLexception;

	/**
	 * To insert field
	 * 
	 * @param potager
	 * @throws BLLexception
	 */
	public void insertField(Field field) throws BLLexception;

	/**
	 * To update field
	 * 
	 * @param field
	 * @throws BLLexception
	 */
	public void updateField(Field field) throws BLLexception;

	/**
	 * To delete field
	 * 
	 * @param field
	 * @throws BLLexception
	 */
	public void deleteField(Field field) throws BLLexception;

	/**
	 * To get all the field
	 * 
	 * @return
	 * @throws BLLexception
	 */
	public List<Field> getAllField() throws BLLexception;

	/**
	 * To insert a plant
	 * 
	 * @param plant
	 * @throws BLLexception
	 */
	public void insertPlant(Plant plant) throws BLLexception;

	/**
	 * To update a plant
	 * 
	 * @param plant
	 * @throws BLLexception
	 */
	public void updatePlant(Plant plant) throws BLLexception;

	/**
	 * To delete a plant
	 * 
	 * @param plant
	 * @throws BLLexception
	 */
	public void deletePlant(Plant plant) throws BLLexception;

	/**
	 * To get all the plant
	 * 
	 * @return
	 * @throws BLLexception
	 */
	public List<Plant> getAllPlant() throws BLLexception;

	/**
	 * To add a plant in a field
	 * 
	 * @param field
	 * @param plant
	 * @throws BLLexception
	 */
	public void addImplentationInField(Field field, Implantation implantation) throws BLLexception;

	/**
	 * To have the informations on a potage
	 * 
	 * @param potager
	 * @return
	 * @throws BLLexception
	 */
	public String infoPotager(Potager potager) throws BLLexception;

	/**
	 * To add activity
	 * 
	 * @param activity
	 * @throws BLLexception
	 */
	public void addActivity(Activity activity) throws BLLexception;

	/**
	 * To have a string of the activities in the 2 next weeks
	 * 
	 * @return
	 * @throws BLLexception
	 */
	public String infoActivityTwoNextWeek() throws BLLexception;

	/**
	 * To get where is a plant with the name
	 * 
	 * @param name
	 * @return
	 * @throws BLLexception
	 */
	public String getPlantLocationsInfoWithName(String name) throws BLLexception;

	/**
	 * To get where is a plant with the name and the variety
	 * 
	 * @param name
	 * @param variety
	 * @return
	 * @throws BLLexception
	 */
	public String getPlantLocationsInfoWithNameAndVariety(String name, String variety) throws BLLexception;

	/**
	 * To remove a plant in a potage
	 * 
	 * @param potager
	 * @param plant
	 * @throws BLLexception
	 */
	public void removePlantInPotager(Potager potager, Plant plant) throws BLLexception;

	/**
	 * get Potager object by Id
	 * 
	 * @param id
	 * @throws BLLexception
	 */
	public Potager getPotagerById(Integer id);

	/**
	 * get Plant object by name
	 * 
	 * @param id
	 * @throws BLLexception
	 */
	public Plant getPlantByName(String first);

	/**
	 * get List of associations
	 * 
	 * @param id
	 * @throws BLLexception
	 */
	public List<Plant> getAllAssociations();

	/**
	 * get List of associations
	 * 
	 * @param id
	 * @throws BLLexception
	 */
	public Field getFieldById(Integer id);
}
