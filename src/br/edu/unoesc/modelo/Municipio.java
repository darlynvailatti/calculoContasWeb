package br.edu.unoesc.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
public @Data class Municipio implements Serializable, MeuModelo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@NotEmpty @NotNull
	private String nome;
	
	@NotNull
	private Estados estado;
}	
