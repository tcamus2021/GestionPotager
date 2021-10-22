package com.formation.eni.gestionPotager.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.eni.gestionPotager.bo.Activity;
import com.formation.eni.gestionPotager.bo.Field;
import com.formation.eni.gestionPotager.bo.Implantation;
import com.formation.eni.gestionPotager.bo.Plant;
import com.formation.eni.gestionPotager.bo.Potager;
import com.formation.eni.gestionPotager.dal.ActivityDAO;
import com.formation.eni.gestionPotager.dal.FieldDAO;
import com.formation.eni.gestionPotager.dal.ImplentationDAO;
import com.formation.eni.gestionPotager.dal.PlantDAO;
import com.formation.eni.gestionPotager.dal.PotagerDAO;

@Service
public class PotagerManagerImpl implements PotagerManager {
	private static Integer PLANTS_LIMIT = 3;

	@Autowired
	private ActivityDAO daoActivity;

	@Autowired
	private PotagerDAO daoPotager;

	@Autowired
	private FieldDAO daoField;

	@Autowired
	private PlantDAO daoPlant;

	@Autowired
	private ImplentationDAO daoImplentation;

	@Override
	@Transactional
	public void insertPotager(Potager potager) throws BLLexception {
		try {
			// TODO Verify if the garden is correct
			potager.getFields().forEach(field -> {
				((Field) field).getImplantations().forEach(implentation -> {
					daoPlant.save(implentation.getPlant());
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
	@Transactional
	public void updatePotager(Potager potager) throws BLLexception {
		try {
			// TODO Verify if the garden is correct
			potager.getFields().forEach(field -> {
				((Field) field).getImplantations().forEach(implentation -> {
					daoPlant.save(implentation.getPlant());
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
	@Transactional
	public void deletePotager(Potager potager) throws BLLexception {
		// TODO delete in cascade
		int sizeBeforeAction = (int) daoPotager.count();
		daoPotager.delete(potager);
		int sizeAfterAction = (int) daoPotager.count();
		if (sizeBeforeAction == sizeAfterAction) {
			throw new BLLexception("BLL/deletePotager(): There isn't this potager in the DataBase");
		}
	}

	@Override
	@Transactional
	public List<Potager> getAllPotager() throws BLLexception {
		return (List<Potager>) daoPotager.findAll();
	}

	@Override
	@Transactional
	public void insertField(Field field) throws BLLexception {
		try {
			// TODO verify the field object
			daoPotager.save(field.getPotager());
			field.getImplantations().forEach(implentation -> {
				daoPlant.save(implentation.getPlant());
				daoImplentation.save(implentation);
			});
			daoField.save(field);
		} catch (Exception e) {
			throw new BLLexception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void updateField(Field field) throws BLLexception {
		try {
			// TODO verify the field object
			daoPotager.save(field.getPotager());
			field.getImplantations().forEach(implentation -> {
				daoPlant.save(implentation.getPlant());
				daoImplentation.save(implentation);
			});
			daoField.save(field);
		} catch (Exception e) {
			throw new BLLexception(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void deleteField(Field field) throws BLLexception {
		// TODO delete in cascade
		int sizeBeforeAction = (int) daoField.count();
		daoField.delete(field);
		int sizeAfterAction = (int) daoField.count();
		if (sizeBeforeAction == sizeAfterAction) {
			throw new BLLexception("BLL/deleteField(): There isn't this field in the DataBase");
		}
	}

	@Override
	@Transactional
	public List<Field> getAllField() throws BLLexception {
		return (List<Field>) daoField.findAll();
	}

	@Override
	@Transactional
	public void insertPlant(Plant plant) throws BLLexception {
		if (!plantNotExist(plant.getName(), plant.getVariety())) {
			throw new BLLexception("BLL/insertPlant(): IMPOSSIBLE, cette plante existe deja");
		}
		daoPlant.save(plant);
	}

	@Override
	@Transactional
	public void updatePlant(Plant plant) throws BLLexception {
		if (!plantNotExist(plant.getName(), plant.getVariety())) {
			throw new BLLexception("BLL/insertPlant(): IMPOSSIBLE, cette plante existe deja");
		}
		daoPlant.save(plant);
	}

	@Override
	@Transactional
	public void deletePlant(Plant plant) throws BLLexception {
		// TODO delete in cascade
		int sizeBeforeAction = (int) daoPlant.count();
		daoPlant.delete(plant);
		int sizeAfterAction = (int) daoPlant.count();
		if (sizeBeforeAction == sizeAfterAction) {
			throw new BLLexception("BLL/deletePotager(): There isn't this potager in the DataBase");
		}
	}

	@Override
	@Transactional
	public List<Plant> getAllPlant() throws BLLexception {
		return (List<Plant>) daoPlant.findAll();
	}

	@Override
	@Transactional
	public void addImplentationInField(Field field, Implantation implantation) throws BLLexception {
		if(!limitOfPlantsNotReached(field, implantation)) {
			throw new BLLexception("BLL/addPlantInField(): IMPOSSIBLE limit of different Plant already reached for this Field");
		}if(!sizeOfPlantsLowerThanField(field, implantation)) {
			throw new BLLexception("BLL/addPlantInField(): IMPOSSIBLE this Plant have not enough place in this Field");
		}
		
		// TODO AJOUT dans l'objet avant la sauvegarde
		
		
	}

	@Override
	@Transactional
	public String infoPotager(Potager potager) throws BLLexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void addActivity(Activity activity) throws BLLexception {
		if (!scheduledDateAfterToday(activity)) {
			throw new BLLexception("BLL/addActivity(): IMPOSSIBLE cannot add postated Activity");
		}
		daoActivity.save(activity);
	}

	@Override
	@Transactional
	public String infoActivityTwoNextWeek() throws BLLexception {
		List<Activity> list = daoActivity.findAllForDateInterval(LocalDate.now(), LocalDate.now().plusWeeks(2));
		StringBuffer sb = new StringBuffer();
		for (Activity activity : list) {
			sb.append("*******").append(activity.toString());
		}
		;
		return sb.toString();
	}

	@Override
	@Transactional
	public String getLocationPlantWithName(String name) throws BLLexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public String getLocationPlantWithNameAndVariety(String name, String variety) throws BLLexception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
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
	private boolean sizeOfPlantsLowerThanField(Field field, Implantation implantation) {
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
		if (daoPlant.findByNameAndVariety(name, variety).size() > 0) {
			return false;
		} else return true;
	}

	/**
	 * Verify if activity have logic date
	 * 
	 * @param activity
	 * @return
	 */
	private boolean scheduledDateAfterToday(Activity activity) {
		if (activity.getDate().isBefore(LocalDate.now())) {
			return false;
		} else
			return true;
	}

	/**
	 * Verify if field is at his limit of plants
	 * 
	 * @param field
	 * @return
	 */
	private boolean limitOfPlantsNotReached(Field field, Implantation implantation) {
		List<Plant> lstPlant = new ArrayList<Plant>();
		for (Implantation implant : field.getImplantations()) {
			if(!lstPlant.contains(implant.getPlant())) {
				lstPlant.add(implant.getPlant());
			}
		}
		if(lstPlant.contains(implantation.getPlant())) {
			return true;
		}
		if(lstPlant.size() > PLANTS_LIMIT) {
			return true;
		}
		return false;
	}
}
