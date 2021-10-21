package com.formation.eni.gestionPotager.bll;

import org.springframework.stereotype.Service;

import com.formation.eni.gestionPotager.bo.Activity;
import com.formation.eni.gestionPotager.bo.Field;
import com.formation.eni.gestionPotager.bo.Potager;

@Service
public class PotagerManagerImpl implements PotagerManager{

	private Integer PLANTS_LIMIT = 3;
	/**
	 * Validation Methods
	 */
	private boolean sumOfFieldsIsLowerThanPotager(Potager potager){
		return true;	
	}
	private boolean sizeOfPlantsLowerThanField(Field field) {
		return true;
	}
	private boolean plantNotExist(String name, String variety) {
		return true; 
	}
	private boolean scheduledDateAfterToday(Activity activity) {
		return true;
	}
	private boolean limitOfPlantsNotReached(Field field) {
		
		return true;
	}
}
