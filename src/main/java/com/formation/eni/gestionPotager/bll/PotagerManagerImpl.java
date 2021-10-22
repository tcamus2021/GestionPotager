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
import com.formation.eni.gestionPotager.dal.ImplantationDAO;
import com.formation.eni.gestionPotager.dal.PlantDAO;
import com.formation.eni.gestionPotager.dal.PotagerDAO;

@Service
public class PotagerManagerImpl implements PotagerManager {
	private static Integer PLANTS_LIMIT = 3;
	private static Integer METER_COEFF = 10000; // for mï¿½
	private static Integer CENTIMETER_COEFF = 1; // for cmï¿½

	@Autowired
	private ActivityDAO daoActivity;

	@Autowired
	private PotagerDAO daoPotager;

	@Autowired
	private FieldDAO daoField;

	@Autowired
	private PlantDAO daoPlant;

	@Autowired
	private ImplantationDAO daoImplantation;

	@Override
	@Transactional
	public void insertPotager(Potager potager) throws BLLexception {
		try {
			potagerValidator(potager);
			potager.getFields().forEach(field -> {
				((Field) field).getImplantations().forEach(implentation -> {
					daoPlant.save(implentation.getPlant());
					daoImplantation.save(implentation);
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
			potagerValidator(potager);
			potager.getFields().forEach(field -> {
				((Field) field).getImplantations().forEach(implentation -> {
					daoPlant.save(implentation.getPlant());
					daoImplantation.save(implentation);
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
				daoImplantation.save(implentation);
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
				daoImplantation.save(implentation);
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
		if (!limitOfPlantsNotReached(field, implantation)) {
			throw new BLLexception(
					"BLL/addPlantInField(): IMPOSSIBLE limit of different Plant already reached for this Field");
		}
		if (!sizeOfPlantsLowerThanField(field, implantation)) {
			throw new BLLexception("BLL/addPlantInField(): IMPOSSIBLE this Plant have not enough place in this Field");
		}

		// TODO AJOUT dans l'objet avant la sauvegarde

	}

	@Override
	@Transactional
	public String infoPotager(Potager potager) throws BLLexception {
		StringBuilder sb = new StringBuilder("[Potager :");
		sb.append(" Name = " + potager.getNom());
		sb.append(", Location = " + potager.getLocation());
		sb.append(", City = " + potager.getCity());
		sb.append(", Aera = " + potager.getAera() + "mÂ²");
		sb.append("\n\tFields = {");
		potager.getFields().forEach(field -> {
			sb.append("\n\t\t" + "Field number " + field.getIdField() + " :");
			sb.append(" Aera = " + field.getAera());
			sb.append(", Ground Type = " + field.getGroundType());
			sb.append(", Exposition Type = " + field.getExpositionType());
			sb.append(",");
		});
		sb.append("\n\t}");
		sb.append("\n]");
		return sb.toString();
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
		return sb.toString();
	}

	@Override
	@Transactional
	public String getPlantLocationsInfoWithName(String name) throws BLLexception {
		List<Implantation> list = (List<Implantation>) daoImplantation.findAllWherePlantIs(name,null);
		StringBuffer sb = new StringBuffer();
		for (Implantation implant : list) {
			sb.append("***"+implant.getPlant().getName()+"***"
					+"\n-- Variety : "+implant.getPlant().getVariety()
					+"\n-- Planted in field : "
					+"\n   exposition - "+implant.getField().getExpositionType()
					+"\n   ground - "+implant.getField().getGroundType()
					+"\n   quantity - "+implant.getNbPlant() 
					+"\n  -> Potager : "+implant.getField().getPotager().getNom()
					+"\n  			-> "+implant.getField().getPotager().getLocation()
					+", "+implant.getField().getPotager().getCity());
		}
		return sb.toString();
	}

	@Override
	@Transactional
	public String getPlantLocationsInfoWithNameAndVariety(String name, String variety) throws BLLexception {
		List<Implantation> list = (List<Implantation>) daoImplantation.findAllWherePlantIs(name,variety);
		StringBuffer sb = new StringBuffer();
		for (Implantation implant : list) {
			sb.append("***"+implant.getPlant().getName()+"***"
					+"\n-- Variety : "+implant.getPlant().getVariety()
					+"\n-- Planted in field : "
					+"\n   exposition - "+implant.getField().getExpositionType()
					+"\n   ground - "+implant.getField().getGroundType()
					+"\n   quantity - "+implant.getNbPlant() 
					+"\n  -> Potager : "+implant.getField().getPotager().getNom()
					+"\n  			-> "+implant.getField().getPotager().getLocation()
					+", "+implant.getField().getPotager().getCity());
		}
		return sb.toString();
	}

	@Override
	@Transactional
	public void removePlantInPotager(Potager potager, Plant plant) throws BLLexception {
		// TODO Auto-generated method stub
		// pour un potager donné
		// 	- dans chaque field
		// 	- dans chaque implantation
		// => remove chaque occurence d'une plant
		
	}

	/**
	 * Verify if the place in the garden is logic
	 * 
	 * @param potager
	 * @return
	 */
	private boolean sizeOfFieldsLowerThanPotager(Potager potager, Field field) {
		Integer sumOfFields = 0;
		for (Field carre : potager.getFields()) {
			sumOfFields += carre.getAera();
		}
		if (sumOfFields + METER_COEFF * field.getAera() > METER_COEFF * potager.getAera()) {
			return false;
		}
		return true;
	}

	/**
	 * Verify if the fields have logic size with plant
	 * 
	 * @param field
	 * @return
	 */
	private boolean sizeOfPlantsLowerThanField(Field field, Implantation implantation) {
		Integer sumOfPlants = 0;
		for (Implantation implant : field.getImplantations()) {
			sumOfPlants += implant.getPlant().getAera() * implant.getNbPlant();
		}
		if (sumOfPlants + CENTIMETER_COEFF * implantation.getPlant().getAera() * implantation.getNbPlant() > METER_COEFF
				* field.getAera()) {
			return false;
		}
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
		} else
			return true;
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
			if (!lstPlant.contains(implant.getPlant())) {
				lstPlant.add(implant.getPlant());
			}
		}
		if (lstPlant.contains(implantation.getPlant())) {
			return false;
		}
		if (lstPlant.size() > PLANTS_LIMIT) {
			return true;
		}
		return false;
	}

	/**
	 * Validate Activity Object
	 * 
	 * @param activity
	 * @return
	 */
	private boolean activityValidator(Activity activity) throws BLLexception {
		return true;
	}

	/**
	 * Validate Field Object
	 * 
	 * @param field
	 * @return
	 */
	private void fieldValidator(Field field) throws BLLexception {
		StringBuilder error = new StringBuilder("");
		if(field.getGroundType() == null) {
			error.append("The ground type is incorect");
		}
		if(field.getPotager() == null) {
			error.append("The potager is incorect");
		}
		if(field.getExpositionType() == null) {
			error.append("The exposition type is incorect");
		}
		if(field.getAera() <= 0 || field.getAera() == null) {
			error.append("The area is incorect");
		}
		
		field.getImplantations().forEach(implantation -> {
			try {
				implantationValidator(implantation);
			} catch (BLLexception e) {
				error.append(e.getMessage());
			}
		});
		
		if (!"".equals(error)) {
			throw new BLLexception(error.toString());
		}
	}

	/**
	 * Validate Implantation Object
	 * 
	 * @param implantation
	 * @return
	 */
	private boolean implantationValidator(Implantation implantation) throws BLLexception {

		return true;
	}

	/**
	 * Validate Plant Object
	 * 
	 * @param plant
	 * @return
	 */
	private boolean plantValidator(Plant plant) throws BLLexception {
		StringBuilder erreur = new StringBuilder("");
		// TODO todo tout
		if (plant.getName() == null || "".equals(plant.getName())) {
	        erreur.append("Pas de nom\n");
	    }
		return true;
	}

	/**
	 * Validate Potager Object
	 * 
	 * @param potager
	 * @return
	 */
	private void potagerValidator(Potager potager) throws BLLexception {
		StringBuilder error = new StringBuilder("");
		if (potager.getNom() == null || "".equals(potager.getNom())) {
			error.append("The name is incorect");
		}
		if (potager.getLocation() == null || "".equals(potager.getLocation())) {
			error.append("The location is incorect");
		}
		if (potager.getCity() == null || "".equals(potager.getCity())) {
			error.append("The city is incorect");
		}
		if (potager.getAera() == null || potager.getAera() <= 0) {
			error.append("The area is incorect");
		}

		potager.getFields().forEach(field -> {
			try {
				fieldValidator(field);
			} catch (BLLexception e) {
				error.append(e.getMessage());
			}
		});

		if (!"".equals(error)) {
			throw new BLLexception(error.toString());
		}
	}
}
