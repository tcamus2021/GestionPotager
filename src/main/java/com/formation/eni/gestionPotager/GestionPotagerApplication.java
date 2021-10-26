package com.formation.eni.gestionPotager;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.formation.eni.gestionPotager.bll.BLLexception;
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
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		// Data garden
		Potager potager1 = new Potager("Chez Rene", "MonPotagerIncroyable", 30, "Penmarc'h");
		Potager potager2 = new Potager("Chez Thierry", "MonIgnoblePotager", 160, "Quimper");
		Potager potager3 = new Potager("Chez Josiane", "MonPotagerSexy", 100, "Nantes");

		// Data field
		Field field1 = new Field(10, GroundType.DIRT, ExpositionType.SUN);
		Field field2 = new Field(10, GroundType.STONE, ExpositionType.SHADOW);
		Field field3 = new Field(10, GroundType.CLAY, ExpositionType.SUN);
		Field field4 = new Field(10, GroundType.DIRT, ExpositionType.SUN);
		Field field5 = new Field(10, GroundType.STONE, ExpositionType.HALF_SHADE);
		Field field6 = new Field(10, GroundType.DIRT, ExpositionType.HALF_SHADE);
		Field field7 = new Field(10, GroundType.CLAY, ExpositionType.SHADOW);

		// garden to field
		potager1.addField(field1);
		potager1.addField(field2);
		potager1.addField(field3);
		potager2.addField(field4);
		potager2.addField(field5);
		potager3.addField(field6);
		potager3.addField(field7);

		// Data plant
		Plant plant1 = new Plant("Carottes", PlantType.VEGETABLE, "Orange", 3);
		Plant plant2 = new Plant("Salade", PlantType.VEGETABLE, "Ice berg", 3);
		Plant plant3 = new Plant("Mangue", PlantType.FRUIT, "Tropical", 3);
		Plant plant4 = new Plant("Patates", PlantType.ROOT, "Nouvelles", 3);
		Plant plant5 = new Plant("Radis", PlantType.VEGETABLE, "Noir", 3);
		Plant plant6 = new Plant("Gimgembre", PlantType.ROOT, "Aphrodisiaque", 3);

		// Data implantation
		Implantation implantation1 = new Implantation(plant1, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation2 = new Implantation(plant1, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation3 = new Implantation(plant2, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation4 = new Implantation(plant2, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation5 = new Implantation(plant3, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation6 = new Implantation(plant3, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation7 = new Implantation(plant4, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation8 = new Implantation(plant4, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation9 = new Implantation(plant5, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation10 = new Implantation(plant5, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation11 = new Implantation(plant6, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));
		Implantation implantation12 = new Implantation(plant6, 2, LocalDate.now(), LocalDate.of(2025, 5, 3));

		// field to implantation
		field1.addImplentation(implantation1);
		field1.addImplentation(implantation2);
		field2.addImplentation(implantation3);
		field2.addImplentation(implantation4);
		field3.addImplentation(implantation5);
		field3.addImplentation(implantation6);
		field4.addImplentation(implantation7);
		field5.addImplentation(implantation8);
		field6.addImplentation(implantation9);
		field6.addImplentation(implantation10);
		field7.addImplentation(implantation11);
		field7.addImplentation(implantation12);

		// insert in db of this data
		try {
			manager.insertPotager(potager1);
			manager.insertPotager(potager2);
			manager.insertPotager(potager3);
			manager.deletePotager(potager3);
			manager.deletePlant(plant4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
