package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

public @Data class ContaRecebidaDoCalculo implements MeuModelo, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7593999196358708167L;
	
	private Long id;
	private Date dataRecebimento;
	private Double valor;
	private Calculo calculo;
	
}
