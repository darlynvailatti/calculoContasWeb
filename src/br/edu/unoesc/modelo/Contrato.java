package br.edu.unoesc.modelo;

import java.io.Serializable;

import lombok.Data;

public @Data class Contrato implements MeuModelo, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4987787747225042516L;
	
	private Long id;
	private String descricao;
}
