package br.edu.unoesc.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity
public @Data class Arquivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private Double tamanho;
	private byte[] conteudo;
	
	public Arquivo(String nome, Double tamanho, byte[] conteudo) {
		super();
		this.nome = nome;
		this.tamanho = tamanho;
		this.conteudo = conteudo;
	}
	
	public Arquivo(){
		
	}
	
	
}
