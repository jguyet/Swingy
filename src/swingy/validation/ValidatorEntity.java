package swingy.validation;

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
			System.out.println("Impossible de valider les donnees du bean : Error information -> ");
			for (ConstraintViolation<Entity> contraintes : constraintViolations) {
				System.out.println("\033[31m" + contraintes.getPropertyPath() + " " + contraintes.getMessage() + "\033[00m");
			}
			System.out.println("}");
			return false;
		}
		
		System.out.println("Les donnees du bean <" + e.getClass().getName() + "> sont valides");
		return true;
	}
}
