package br.edu.unoesc.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;

import br.edu.unoesc.modelo.Usuario;
import br.edu.unoesc.util.Criptografia;

public class UsuarioDAO extends GenericDAO<Usuario>{

	public UsuarioDAO(Class<Usuario> minhaClasse) {
		super(minhaClasse);
		// TODO Auto-generated constructor stub
	}
	
	
	public Usuario getByLoginAndPassword(Usuario usuario) throws NoSuchAlgorithmException {
		
		TypedQuery<Usuario> query =
				em.createNamedQuery("getByLoginAndPassword", Usuario.class);
		
//		Login usuario
		query.setParameter("loginParam", usuario.getLogin());
		query.setParameter("passwordParam", usuario.getPassword());
				
//		Se pesquisa nao estiver vazia, existe usuario com dados informados
		if(!query.getResultList().isEmpty()){
			return query.getResultList().get(0);
		}
//		Nenhum usuario identificado
		return null;
	}
	
	
	
	public Usuario getByLogin(Usuario usuario){
		TypedQuery<Usuario> query =
				em.createNamedQuery("getByLogin", Usuario.class);
		
//		Login usuario
		query.setParameter("loginParam", usuario.getLogin());
//		Se pesquisa nao estiver vazia, existe usuario com dados informados
		if(!query.getResultList().isEmpty()){
			return query.getResultList().get(0);
		}
//		Nenhum usuario identificado
		return null;
	}
	
	@Override
	public Usuario salvar(Usuario tipo) throws SQLException {
		EntityTransaction et = em.getTransaction();
		et.begin();
		if (tipo.getLogin() == null) { // pessoa nao existe
			em.persist(tipo);	// inserir
		} else {
			em.merge(tipo); // alterar
		}
		et.commit();
		return tipo;
	}
	
	public void excluir(String login){
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Usuario usuario = em.find(Usuario.class, login);
			em.remove(usuario);
			et.commit();
		} catch (ConstraintViolationException ex) {
			if(et.isActive()){
				et.rollback();
			}
		}
	}
	
	
}
