package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

public @Data class Calculo implements MeuModelo, Serializable {
	private Long id;
	private Date data;
	private Date dataValidade;
	private Double valorCondenado;
	private Double taxa;
	private Date atualizacaoAte;
	private Situacao situacao;
	private Metodologia metodologia;
	private List<ServicoPrestadoCalculo> servicosPrestados = new ArrayList<>();	
	private List<ContaRecebidaDoCalculo> contasRecebidas = new ArrayList<>();
	
	
	
	/** Métodos de negócio */
	public Double calcularValorAReceber(){
		return 0d;
	}
}
