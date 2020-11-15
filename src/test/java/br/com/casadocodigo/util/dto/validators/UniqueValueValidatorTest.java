package br.com.casadocodigo.util.dto.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.validation.Payload;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import br.com.casadocodigo.domain.autor.model.Autor;

public class UniqueValueValidatorTest {
	
	@DisplayName("Non existent values")
	@ParameterizedTest(name = "should {arguments} not exist already ")
    @ValueSource(strings = {"nonExistentValue"})
	public void test1(String email) {
		EntityManager manager = Mockito.mock(EntityManager.class, Mockito.RETURNS_DEEP_STUBS);
		Mockito.when(manager.createQuery("SELECT t FROM Autor t where t.email = :value").setParameter("value", email).getResultStream())
			.thenReturn(Stream.empty());
		UniqueValueValidator uniqueValueValidator = new UniqueValueValidator(manager);
		uniqueValueValidator.initialize(extracted("Autor", "email"));
		assertTrue(uniqueValueValidator.isValid(email, null));
	}
	
	@DisplayName("Existent values")
	@ParameterizedTest(name = "should {arguments} exist already ")
    @ValueSource(strings = {"existentValue"})
	public void test2(String email) {
		EntityManager manager = Mockito.mock(EntityManager.class, Mockito.RETURNS_DEEP_STUBS);
		Mockito.when(manager.createQuery("SELECT t FROM Autor t where t.email = :value").setParameter("value", email).getResultStream())
			.thenReturn(Stream.of(new Autor()));
		UniqueValueValidator uniqueValueValidator = new UniqueValueValidator(manager);
		uniqueValueValidator.initialize(extracted("Autor", "email"));
		assertFalse(uniqueValueValidator.isValid(email, null));
	}
	
	private UniqueValue extracted(String tableName, String columnName) {
		return new UniqueValue() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return null;
			}
			
			@Override
			public String tableName() {
				return tableName;
			}
			
			@Override
			public Class<? extends Payload>[] payload() {
				return null;
			}
			
			@Override
			public String message() {
				return null;
			}
			
			@Override
			public Class<?>[] groups() {
				return null;
			}
			
			@Override
			public String columnName() {
				return columnName;
			}
		};
	}
}
