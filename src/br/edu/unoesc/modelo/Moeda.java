package br.edu.unoesc.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
public @Data class Moeda implements MeuModelo, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1366309305981526600L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull @Size(min=1)
	private String descricao;
	@NotNull @Size(min=2)
	private String sigla;
	
}
