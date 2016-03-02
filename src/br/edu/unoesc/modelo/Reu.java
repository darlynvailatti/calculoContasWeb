package br.edu.unoesc.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@DiscriminatorValue(value="R")
public @Data class Reu extends Pessoa {

}
