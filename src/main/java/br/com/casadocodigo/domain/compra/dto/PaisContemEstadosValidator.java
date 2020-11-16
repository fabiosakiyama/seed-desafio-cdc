package br.com.casadocodigo.domain.compra.dto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.casadocodigo.domain.estado.model.Estado;
import br.com.casadocodigo.domain.pais.model.Pais;
import br.com.casadocodigo.domain.pais.repository.PaisRepository;

/*
 * 9 (max 9)
 */
@Component
public class PaisContemEstadosValidator implements Validator{
	
	//1
	private PaisRepository paisRepository;
	
	public PaisContemEstadosValidator( PaisRepository paisRepository) {
		this.paisRepository = paisRepository;
	}

	//1
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(NovaCompraRequest.class);
	}

	// 7
	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		NovaCompraRequest request = (NovaCompraRequest) target;
		
		Optional<Pais> possivelPais = paisRepository.findById(request.getPaisId());
		if(possivelPais.isEmpty()) {
			errors.reject("exists.id");
			return;
		}
		
		List<Estado> estados = possivelPais.get().getEstados();
		if(estados.isEmpty() && request.getEstadoId() == null) {
			return;
		}
		
		if(estados.stream().noneMatch(e -> e.getId().equals(request.getEstadoId()))){
			errors.rejectValue("estadoId", "state.does.not.belong.to.country", new String[] {possivelPais.get().getNome()},  "state.does.not.belong.to.country");
			return;
		}
	}
}
