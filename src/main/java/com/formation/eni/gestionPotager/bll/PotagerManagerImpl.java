package com.formation.eni.gestionPotager.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formation.eni.gestionPotager.bo.Activity;
import com.formation.eni.gestionPotager.bo.Field;
import com.formation.eni.gestionPotager.bo.Plant;
import com.formation.eni.gestionPotager.bo.Potager;

@Service
public class PotagerManagerImpl implements PotagerManager {

	private static Integer PLANTS_LIMIT = 3;

	@Override
	public void insertPotager(Potager potager) throws BLLexception {
		System.out.println("insert");
	}

	@Override
	public void updatePotager(Potager potager) throws BLLexception {
		// TODO Auto-generated method 
		System.out.println("updatePotager test conflict!");
	}

	@Override
	public void deletePotager(Potager potager) throws BLLexception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Potager> getAllPotager() throws BLLexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertField(Field field) throws BLLexception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateField(Field field) throws BLLexception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteField(Field field) throws BLLexception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Field> getAllField() throws BLLexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertPlant(Plant plant) throws BLLexception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePlant(Plant plant) throws BLLexception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePlant(Plant plant) throws BLLexception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Plant> getAllPlant() throws BLLexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPlantInField(Field field, Plant plant) throws BLLexception {
		// TODO Auto-generated method stub

	}

	@Override
	public String infoPotager(Potager potager) throws BLLexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addActivity(Activity activity) throws BLLexception {
		// TODO Auto-generated method stub

	}

	@Override
	public String infoActivityTwoNextWeek() throws BLLexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocationPlantWithName(String name) throws BLLexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocationPlantWithNameAndVariety(String name, String variety) throws BLLexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePlantInPotager(Potager potager, Plant plant) throws BLLexception {
		// TODO Auto-generated method stub

	}

	/**
	 * Verify if the place in the garden is logic
	 * 
	 * @param potager
	 * @return
	 */
	private boolean sumOfFieldsIsLowerThanPotager(Potager potager) {
		// TODO
		return true;
	}

	/**
	 * Verify if the fields have logic size with plant
	 * 
	 * @param field
	 * @return
	 */
	private boolean sizeOfPlantsLowerThanField(Field field) {
		// TODO
		return true;
	}

	/**
	 * If plant exists
	 * 
	 * @param name
	 * @param variety
	 * @return
	 */
	private boolean plantNotExist(String name, String variety) {
		// TODO
		return true;
	}

	/**
	 * Verify if activity have logic date
	 * 
	 * @param activity
	 * @return
	 */
	private boolean scheduledDateAfterToday(Activity activity) {
		// TODO
		return true;
	}

	/**
	 * Verify if field is at his limit of plants
	 * 
	 * @param field
	 * @return
	 */
	private boolean limitOfPlantsNotReached(Field field) {
		// TODO
		return true;
	}
}
