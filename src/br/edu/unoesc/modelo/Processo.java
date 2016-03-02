package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
public @Data class Processo implements MeuModelo, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8852111961312401693L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="processo")
	@TableGenerator(
	        name="processo", allocationSize=1)
	private Long id;
	
	@NotNull
	@ElementCollection
	private List<String> numeros = new ArrayList<>();
	@NotNull
	@ElementCollection
	private List<String> numeroTribunal = new ArrayList<>();
	
	@OneToOne(targetEntity=Reu.class)
	@NotNull(message ="Apenas existe um processo se existir um ou mais Réu relacionado")
	private Reu reu;
	
	@NotNull @NotEmpty
	private String titulo;
	@NotNull @NotEmpty
	private String descricao;
	@NotNull @NotEmpty
	private String numeroPadrao;
	
	@Temporal(TemporalType.DATE)
	private Date dataTransitoEmJulgado;
	
	@Temporal(TemporalType.DATE)
	private Date dataPropositura;
	
	@Temporal(TemporalType.DATE)
	private Date dataDespachoInaugural;
	
	@OneToOne(targetEntity=Categoria.class)
	private Categoria categoria;
	
	@NotNull
	@ElementCollection
	private List<String> numeroStj = new ArrayList<>();
	
	@OneToOne(targetEntity=Comarca.class) @NotNull
	private Comarca comarca;

	@OneToMany(targetEntity=Documento.class, cascade = CascadeType.ALL)
	private List<Documento> documentos;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
	
}
