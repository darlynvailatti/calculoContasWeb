package br.edu.unoesc.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;


@Entity
@DiscriminatorValue("E")
public @Data class Escritorio extends Pessoa {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(targetEntity=Advogado.class)
	private List<Advogado> advogados = new ArrayList<>();
	
	@OneToMany(targetEntity=ContratoEscritorio.class, cascade = CascadeType.ALL)
	private List<ContratoEscritorio> contratos = new ArrayList<>();
}
