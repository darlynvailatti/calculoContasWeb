package br.edu.unoesc.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
public @Data class Metodologia implements MeuModelo, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3468489991368529104L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@TableGenerator(name="hibernate_sequences",pkColumnName="metodologia",allocationSize=1)
	private Long id;
	@NotNull @Size(min=1, message="Tamanho deve ser maior que 1")
	private String descricao;
	
}
