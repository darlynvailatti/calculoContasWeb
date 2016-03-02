package br.edu.unoesc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class BaseDAO {

	public static final EntityManagerFactory emf =Persistence.createEntityManagerFactory("calculoContasWeb");;
	protected final EntityManager em;
	
	public BaseDAO() {
		em = emf.createEntityManager();
	}
	
	public void fechar() {
		em.close();
//		emf.close();
	}
	
}




