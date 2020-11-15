package br.com.casadocodigo.domain.compra.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.casadocodigo.domain.compra.model.Compra;
import lombok.Data;

/*
 * 3 (max 9)
 */
@Data
public class NovaCompraResponse {
	
	private String email;
	
	private String nome;
	
	private String sobreNome;
	
	private String documento;
	
	private String endereco;
	
	private String complemento;
	
	private String cidade;
	
	private String pais;
	
	private String estado;
	
	private String telefone;
	
	private String cep;
	
	private BigDecimal total;
	
	private BigDecimal totalComDesconto;
	
	private boolean existeCupom;
	
	private Integer valorPercentualCupom;
	
	private String codigoCupom;
	
	private List<NovaCompraItemResponse> itens = new ArrayList<>();

	//1
	public NovaCompraResponse(Compra compra) {
		this.email = compra.getEmail();
		this.nome = compra.getNome();
		this.sobreNome = compra.getSobreNome();
		this.documento = compra.getDocumento();
		this.endereco = compra.getEndereco();
		this.complemento = compra.getComplemento();
		this.cidade = compra.getCidade();
		this.pais = compra.getPais().getNome();
		this.estado = (compra.getEstado() != null ? compra.getEstado().getNome() : null);
		this.telefone = compra.getTelefone();
		this.cep = compra.getCep();
		this.total = compra.getTotal();
		this.existeCupom = compra.getCupom() != null;
		//1
		if(existeCupom) {
			this.valorPercentualCupom = compra.getCupom().getPercentualDesconto();
			this.codigoCupom = compra.getCupom().getCodigo();
			this.totalComDesconto = compra.getTotalComDesconto();
		}
		//1
		this.itens = compra.getItens().stream().map(i -> new NovaCompraItemResponse(i.getNomeLivro(), i.getQuantidade())).collect(Collectors.toList());
	}
}
