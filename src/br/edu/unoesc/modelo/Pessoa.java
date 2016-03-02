package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import lombok.Data;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public @Data class Pessoa implements MeuModelo, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3292572583095553942L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="pessoa")
	@TableGenerator(
	        name="pessoa", allocationSize=1)
	protected Long id;
	protected String nome;
	protected String cpf;
	protected String cnpj;
	protected String razaoSocial;
	
	@OneToMany(targetEntity = PessoaContato.class)
	protected List<PessoaContato> contatos = new ArrayList<>();
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
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
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	
}
