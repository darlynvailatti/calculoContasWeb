package br.edu.unoesc.modelo;

import java.io.Serializable;

import lombok.Data;

public @Data class Artigo implements MeuModelo, Serializable{
	private Long id;
	private String numero;
	private String descricao;
	
}
