package com.formation.eni.gestionPotager.bll;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.eni.gestionPotager.bo.Activity;
import com.formation.eni.gestionPotager.bo.Field;
import com.formation.eni.gestionPotager.bo.Plant;
import com.formation.eni.gestionPotager.bo.Potager;
import com.formation.eni.gestionPotager.dal.FieldDAO;
import com.formation.eni.gestionPotager.dal.ImplentationDAO;
import com.formation.eni.gestionPotager.dal.PotagerDAO;
import com.formation.eni.gestionPotager.dal.ActivityDAO;

@Service
public class PotagerManagerImpl implements PotagerManager {
	private static Integer PLANTS_LIMIT = 3;
	
	@Autowired
	ActivityDAO daoActivity;

	@Autowired
	private PotagerDAO daoPotager;

	@Autowired
	private FieldDAO daoField;

	@Autowired
	private ImplentationDAO daoImplentation;

	@Override
	public void insertPotager(Potager potager) throws BLLexception {
		try {
			// TODO Verify if the garden is correct
			potager.getFields().forEach(field -> {
				((Field) field).getImplentations().forEach(implentation -> {
					daoImplentation.save(implentation);
				});
				daoField.save(field);
			});
			daoPotager.save(potager);
		} catch (Exception e) {
			throw new BLLexception(e.getMessage());
		}
	}

	@Override
	public void updatePotager(Potager potager) throws BLLexception {
		try {
			// TODO Verify if the garden is correct
			potager.getFields().forEach(field -> {
				((Field) field).getImplentations().forEach(implentation -> {
					daoImplentation.save(implentation);
				});
				daoField.save(field);
			});
			daoPotager.save(potager);
		} catch (Exception e) {
			throw new BLLexception(e.getMessage());
		}
	}

	@Override
	public void deletePotager(Potager potager) throws BLLexception {
		int sizeBeforeAction = (int) daoPotager.count();
		daoPotager.delete(potager);
		int sizeAfterAction = (int) daoPotager.count();
		if (sizeBeforeAction == sizeAfterAction) {
			throw new BLLexception("There isn't this potager in the DataBase");
		}
	}

	@Override
	public List<Potager> getAllPotager() throws BLLexception {
		return (List<Potager>) daoPotager.findAll();
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
		if(!scheduledDateAfterToday(activity)) {
			throw new BLLexception("Impossible d'insérer une activité postdaté");	
		}
		daoActivity.save(activity);	
	}

	@Override
	public String infoActivityTwoNextWeek() throws BLLexception {
		List<Activity> list = daoActivity.findAllForDateInterval(LocalDate.now(), LocalDate.now().plusWeeks(2));
		StringBuffer sb = new StringBuffer();
		for (Activity activity : list) {
			sb.append(activity.toString());
			sb.append("*******");
		};
		return sb.toString();
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
