package com.formation.eni.gestionPotager;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.formation.eni.gestionPotager.bo.Activity;
import com.formation.eni.gestionPotager.bo.Field;
import com.formation.eni.gestionPotager.bo.Implentation;
import com.formation.eni.gestionPotager.bo.Lieu;
import com.formation.eni.gestionPotager.bo.Plant;
import com.formation.eni.gestionPotager.bo.Potager;

@SpringBootApplication
public class GestionPotagerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GestionPotagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Lieu potager = new Potager();
		
		Activity act = new Activity(LocalDate.of(2022, 23, 5), "La kermes du potager !", potager);
		
		Field carre = new Field();
		
		Implentation plantePlantee = new Implentation();
		
		Plant plante = new Plant();
		
	}

}
