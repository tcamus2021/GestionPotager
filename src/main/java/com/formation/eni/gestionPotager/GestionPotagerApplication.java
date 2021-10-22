package com.formation.eni.gestionPotager;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.formation.eni.gestionPotager.bll.PotagerManager;
import com.formation.eni.gestionPotager.bo.Activity;
import com.formation.eni.gestionPotager.bo.ExpositionType;
import com.formation.eni.gestionPotager.bo.Field;
import com.formation.eni.gestionPotager.bo.GroundType;
import com.formation.eni.gestionPotager.bo.Implantation;
import com.formation.eni.gestionPotager.bo.Plant;
import com.formation.eni.gestionPotager.bo.PlantType;
import com.formation.eni.gestionPotager.bo.Potager;

@SpringBootApplication
public class GestionPotagerApplication implements CommandLineRunner {

	@Autowired
	PotagerManager manager;

	public static void main(String[] args) {
		SpringApplication.run(GestionPotagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Potager potager = new Potager("c'est ici ! l� !", "SoupeurGreenPotage", 30, "Mon-Sac");
		Field carre = new Field(5, GroundType.DIRT, ExpositionType.SUN);
		potager.addField(carre);
		
		Plant plante = new Plant("Choux", PlantType.VEGETABLE, "normaux", 5);
		Implantation plantePlantee = new Implantation(plante, 4, LocalDate.now(), LocalDate.of(2030, 5, 23), carre);
		carre.addImplentation(plantePlantee);
		Activity act = new Activity(LocalDate.of(2022, 5, 23), "La kermes du potager !", potager);

		try {
			manager.insertPotager(potager);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Field carre2 = new Field(3, GroundType.STONE, ExpositionType.SHADOW);
		potager.addField(carre2);
		manager.insertField(carre2);
	}

}
