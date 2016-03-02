package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

public @Data class ServicoPrestadoCalculo implements MeuModelo, Serializable {
	private Long id;
	private Date data;
	private Double valor;
	private Calculo calculo;
	private Servico servico;
	
}
