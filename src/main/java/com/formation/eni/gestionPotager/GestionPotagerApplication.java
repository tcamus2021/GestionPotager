package com.formation.eni.gestionPotager;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.formation.eni.gestionPotager.bo.Activity;
import com.formation.eni.gestionPotager.bo.ExpositionType;
import com.formation.eni.gestionPotager.bo.Field;
import com.formation.eni.gestionPotager.bo.GroundType;
import com.formation.eni.gestionPotager.bo.Implentation;
import com.formation.eni.gestionPotager.bo.Plant;
import com.formation.eni.gestionPotager.bo.PlantType;
import com.formation.eni.gestionPotager.bo.Potager;

@SpringBootApplication
public class GestionPotagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestionPotagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Potager potager = new Potager("c'est ici ! là !", "SoupeurGreenPotage", 30, "Mon-Sac");
		Activity act = new Activity(LocalDate.of(2022, 5, 23), "La kermes du potager !", potager);
		
		Field carre = new Field(potager, 5, GroundType.DIRT, ExpositionType.SUN);
		
		Plant plante = new Plant("Choux", PlantType.VEGETABLE, "normaux", 5);
		Implentation plantePlantee = new Implentation(plante, 4, LocalDate.now(), LocalDate.of(2030, 5, 23), carre);
		
	}

}
