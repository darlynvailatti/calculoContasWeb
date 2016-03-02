package br.edu.unoesc.modelo;

import java.io.Serializable;

import lombok.Data;

public @Data class AplicacaoArtigo implements MeuModelo, Serializable{
	private Long id;
	private ProcessoAutor processoAutor;
	private Artigo artigo;
}
