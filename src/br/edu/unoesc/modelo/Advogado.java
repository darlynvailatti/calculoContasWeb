package br.edu.unoesc.modelo;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@DiscriminatorValue(value="A")
public @Data class Advogado extends Pessoa implements MeuModelo, Serializable {
	private Integer numeroOab;
	private Estados estadoOab;
	
}
