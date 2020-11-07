package br.com.casadocodigo.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import br.com.casadocodigo.autor.dto.UniqueEmailValidator;
import br.com.casadocodigo.autor.model.Autor;
import br.com.casadocodigo.autor.repository.AutorRepository;

public class UniqueEmailValidatorTest {
	
	@DisplayName("Valid Email validation")
	@ParameterizedTest(name = "should {arguments} be a valid email ")
    @ValueSource(strings = {"valid@email.com", "another@valid.email", "a.a@a.com"})
	public void test1(String email) {
		AutorRepository repository = Mockito.mock(AutorRepository.class);
		assertTrue(new UniqueEmailValidator(repository).isValid(email, null));
	}
	
	@DisplayName("Invalid Email validation")
	@ParameterizedTest(name = "should {arguments} be an invalid email ")
    @ValueSource(strings = {"invalidemail.com", "another@in@valid.email", ""})
	public void test2(String email) {
		AutorRepository repository = Mockito.mock(AutorRepository.class);
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(new Autor()));
		assertFalse(new UniqueEmailValidator(repository).isValid(email, null));
	}
}
