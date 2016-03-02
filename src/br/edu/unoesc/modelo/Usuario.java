package br.edu.unoesc.modelo;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;

import br.edu.unoesc.util.Criptografia;
import lombok.Data;

@Entity
@NamedQueries({
		@NamedQuery(name="getByLoginAndPassword", query="SELECT u FROM Usuario u WHERE u.login = :loginParam and u.password = :passwordParam"),
		@NamedQuery(name="getByLogin", query="SELECT u FROM Usuario u WHERE u.login = :loginParam")
})	
public @Data class Usuario implements MeuModelo, Serializable{
	@Id
	private String login;
	@NotNull @Size(min=1, message="Senha inválida")
	private String password;
	private String nome;
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setPassword(String password) throws NoSuchAlgorithmException{
		this.password = Criptografia.convertStringToMD5(password);
		System.out.println(this.password);
	}

	public void setLogin(String login){
		this.login = login.toLowerCase();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
}
