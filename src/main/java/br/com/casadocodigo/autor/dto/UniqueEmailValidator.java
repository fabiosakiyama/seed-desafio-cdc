package br.com.casadocodigo.autor.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.casadocodigo.autor.repository.AutorRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
 
    private AutorRepository repository;
 
    public UniqueEmailValidator(AutorRepository repository) {
        this.repository = repository;
    }
 
    public void initialize(UniqueEmail constraint) {
    }
 
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !repository.findByEmail(email).isPresent();
    }
}
