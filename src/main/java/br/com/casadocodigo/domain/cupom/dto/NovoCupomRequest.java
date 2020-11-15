package br.com.casadocodigo.domain.cupom.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.casadocodigo.domain.cupom.model.Cupom;
import br.com.casadocodigo.util.dto.validators.UniqueValue;
import lombok.Data;

@Data
public class NovoCupomRequest {
	
	@NotBlank
	@UniqueValue(tableName = "Cupom", columnName = "codigo")
	private String codigo;
	
	@NotNull
	@Positive
	@Max(value = 100)
	private Integer percentualDesconto;
	
	@Future
	@NotNull
	private LocalDate validadeDoCupom;

	public Cupom toModel() {
		return new Cupom(codigo, percentualDesconto, validadeDoCupom);
	}
}
