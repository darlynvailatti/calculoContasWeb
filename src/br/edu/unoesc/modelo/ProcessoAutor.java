package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

public @Data class ProcessoAutor implements MeuModelo, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4033957585805803598L;
	
	private Long id;
	private Autor autor;
	private Processo processo;
	private Date dataAbertura;
	private Pessoa responsavel; // Aquele que vai efetuar calculos, funcionario da empresa
	private Double valorCausa;
	private Date dataPropositura;
	private String observacao;
	private Boolean ajg;
	private Double ajgValor;
	private Date ajgDataPagamento;
	private Date despachoInalgural;
	private Date citacaoEm;
	private String citacaoFls;
	private Boolean citacaoContestacao;
	private Boolean inversaoOnusDaProva;
	private String inversaoOnusDaProvaFls;
	private Boolean despachoSaneador;
	private String despachoSaneadorFls;
	private Boolean radiografia;
	private Boolean certidao;
	private Integer certidaoRadiografiaNumero;
	private String certidaoRadiografiaFls;
	private Date certidaoRadiografiaData;
	private Double radiografiaValor;
	private Boolean radiografiaConfereComContrato;
	private Boolean radiografiaAdulterada;
	private Reu acoesSubscritasCapitalizadasPor;
	private Integer acoesOnCapitalizadas;
	private Integer acoesPnCapitalizadas;
	private Date dataSubscricao;
	private Boolean embargosDeclaradosAutor;
	private Boolean embargosDeclaradosReu;
	private NivelProvento embargosDeclaradosAutorNivel;
	private NivelProvento embargosDeclaradosReuNivel;
	private String embargosDeclaradosAutorFls;
	private String embargosDeclaradosReuFls;
	private Date dataTransitoEmJulgado;
	private String dataTransitoEmJulgadoFls;
	private Boolean calculosDaRe;
	private String calculosDaReFls;
	private String calculosDaReObs;
	private Moeda moedaDaEpoca;
	private ContratoProcessoAutor contratoProcessoAutor;
	private List<AplicacaoArtigo> artigosAplicados = new ArrayList<>();
	private List<Calculo> calculos = new ArrayList<>();
	
	
}
