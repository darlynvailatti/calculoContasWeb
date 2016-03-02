package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

public @Data class ContratoProcessoAutor implements MeuModelo, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4092066365996638535L;
	
	private Long id;
	private Integer numero;
	private Boolean contratoOficial;
	private Double valorPago;
	private Moeda moedaDaEpoca;
	private Date dataContrato;
	private TipoPagamento pagamento;
	
}
