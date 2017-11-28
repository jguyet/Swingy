package swingy.validation;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.bootstrap.ProviderSpecificBootstrap;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;

import swingy.entity.Entity;

public class ValidatorEntity {
	
	private static ProviderSpecificBootstrap<HibernateValidatorConfiguration> psb = Validation.byProvider(HibernateValidator.class);
	private static Configuration<?> configuration = psb.configure();
	
	private static ValidatorFactory factory = configuration.buildValidatorFactory();//Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();

	public static boolean validateEntity(Entity e) {
		
		Set<ConstraintViolation<Entity>> constraintViolations = validator.validate(e);
		 
		if (constraintViolations.size() > 0 ) {
			System.out.println("\033[31m{");
			System.out.println("	Impossible de valider les donnees du bean <" + e.getClass().getName() + ">(\"" + e.getName() + "\") : Error information -> ");
			for (ConstraintViolation<Entity> contraintes : constraintViolations) {
				System.out.println("	" + contraintes.getPropertyPath() + " " + contraintes.getMessage() + "");
			}
			System.out.println("}\033[00m");
			return false;
		}
		
		System.out.println("\033[32m{Les donnees du bean <" + e.getClass().getName() + ">(\"" + e.getName() + "\") sont valides}\033[00m");
		return true;
	}
	
	public static ArrayList<Entity> valideEntityList(ArrayList<Entity> list) {
		ArrayList<Entity> entities = new ArrayList<Entity>();
		
		System.out.println("=========== Test des Beans ============");
		for (Entity e : list) {
			if (ValidatorEntity.validateEntity(e)) {
				entities.add(e);
			}
		}
		System.out.println("=======================================");
		return (entities);
	}
}
