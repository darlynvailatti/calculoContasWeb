package br.edu.unoesc.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class PessoaContato implements MeuModelo, Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 3977548965706590969L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private ContatoTipo contatoTipo;
	private String numero;
	private String descricao;
}
