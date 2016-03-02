package br.edu.unoesc.dao;

import java.util.List;


import br.edu.unoesc.modelo.Metodologia;

public class MetodologiaDAO extends GenericDAO<Metodologia>{

	public MetodologiaDAO(Class<Metodologia> minhaClasse) {
		super(minhaClasse);
		// TODO Auto-generated constructor stub
	}
	
	public List<Metodologia> listar(){
		List<Metodologia> metodologias = em.createQuery("From Metodologia").getResultList();
		return metodologias;
	}	

}
