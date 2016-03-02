package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

public @Data class ProcessoAutorSentenca implements MeuModelo, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7883424464702293520L;
	
	private Long id;
	private ProcessoAutor processoAutor;
	private Date sentencaoEm;
	private String fls;
	private Boolean procedente;
	private String observacao;
	private Boolean subscreverAcoes;
	private Date subscreverAcoesNoPrazoDe;
	private VpaSentenca vpaSentenca;
	private Boolean dividendos;
	private String dividendosFls;
	private String outrosFrutos;
	private Boolean jcp;
	private Boolean bonificacoes;
	private Boolean dobraAcionaria;
	private Double honorariosValor;
	private Double honorariosPercentual;
	private Boolean jurosDeMoraSobreDifAcoes;
	private Double jurosDeMoraSobreDifAcoesAPartirDe;
	private Character jurosDeMoraSobreDifAcoesQuando;
	private Boolean jurosDeMoraSobreProventos;
	private Double jurosDeMoraSobreProventosAPartirDe;
	private Character jurosDeMoraSobreProventoQuando;
	private Boolean correcaoEmPecunia;
	private String correcaoEmPecuniaDescricao;
	private Boolean contratoDeAdesao;
	private String contratoDeAdesaoFls;
	private Boolean cdc;
	private String cdcFls;
	
	
}
