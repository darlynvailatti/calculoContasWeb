package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

public @Data class ProcessoAutorTribunalJustica implements MeuModelo, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4074372758492022733L;
	
	private Long id;
	private ProcessoAutor processoAutor;
	private String acordaoNumero;
	private String acordaoFls;
	private NivelProvento acordaoNivelProvento;
	private Boolean subscreverAcoes;
	private Date subscreverAcoesPrazo;
	private Boolean subscreverAcoesDeOficio;
	private CriterioTribunalJustica criterioTj;
	private String criterioTjObs;
	private Boolean dividendos;
	private String dividendosObs;
	private Boolean conversaoEmPerdasEDanos;
	private String conversaoEmPerdasEDanosFls;
	private String observacao;
}
