package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
public @Data class ContratoEscritorio implements MeuModelo, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6286173544927782817L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	private Contrato contrato;
	private Double valorFixo;
	private Double percentual;
	private Date dataVigencia;
}
