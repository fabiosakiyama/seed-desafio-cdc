package br.com.casadocodigo.util.dto.validators;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

	private EntityManager manager;

	private Class<?> klass;

	public ExistsIdValidator(EntityManager manager) {
		this.manager = manager;
	}

	public void initialize(ExistsId constraint) {
		this.klass = constraint.klass();
	}

	public boolean isValid(Long value, ConstraintValidatorContext context) {
		return value != null && manager.find(klass, value) != null;
	}
}
