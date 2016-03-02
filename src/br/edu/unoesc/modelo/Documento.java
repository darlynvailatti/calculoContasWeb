package br.edu.unoesc.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;


@Entity
public @Data class Documento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	private String descricao;
	
	@OneToMany(targetEntity = Arquivo.class)
	private List<Arquivo> anexos;
}
