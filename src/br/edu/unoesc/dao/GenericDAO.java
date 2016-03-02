package br.edu.unoesc.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.jpa.HibernateEntityManager;

import br.edu.unoesc.modelo.MeuModelo;

public class GenericDAO<T extends MeuModelo> extends BaseDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2712338549302997933L;
	
	private Class<T> tipoDeClasse;
	
	
	public GenericDAO(Class<T> minhaClasse) {
		this.tipoDeClasse = minhaClasse;
	}
	
	public void excluir(Long codigo) {
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			T objeto = em.find(this.tipoDeClasse, codigo);
			em.remove(objeto);
			et.commit();
		} catch (ConstraintViolationException ex) {
			if(et.isActive()){
				et.rollback();
			}
		}
	}
	
	public T salvar(T tipo) throws SQLException {
		EntityTransaction et = em.getTransaction();
		et.begin();
		if (tipo.getId() == null) { // pessoa nao existe
			em.persist(tipo);	// inserir
		} else {
			em.merge(tipo); // alterar
		}
		et.commit();
		return tipo;
	}
	
	public T getById(T tipo, Class<T> objectClass){
		return em.find(objectClass, tipo.getId());		
	}
	
	public List<T> listar(){
		List<T> objs = em.createQuery("From " + this.tipoDeClasse.getName()).getResultList();
		return objs;
	}	

	
	public Connection getConnection(){
		
		HibernateEntityManager hem = em.unwrap(HibernateEntityManager.class);
		Session session = hem.getSession();
		SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) session.getSessionFactory();
		ConnectionProvider connectionProvider = sessionFactoryImplementation.getConnectionProvider();
		try {
			return connectionProvider.getConnection();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
}























