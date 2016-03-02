package br.edu.unoesc.controller.generic;

import java.util.List;

import lombok.Data;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Advogado;
import br.edu.unoesc.modelo.MeuModelo;
import br.edu.unoesc.util.MessageJsf;

public @Data class GenericBean<T extends MeuModelo>{
	private Class<T> classTipo;
	
//	DAO
	private GenericDAO<T> objetoDao;
	
	
//	Construtor
	public GenericBean(Class<T> classTipo) throws InstantiationException, IllegalAccessException{
		this.classTipo = classTipo;
		this.objetoDao = new GenericDAO<>(classTipo);
	}
	
	public void salvar(T objeto) {
		if(objeto != null){
			try{
				objeto = objetoDao.salvar(objeto);
				MessageJsf.messageInfo("Registro salvo com sucesso");
			}catch(Exception e){
				e.printStackTrace();
				MessageJsf.messageError(e.getMessage());
			}
		}else{
			MessageJsf.messageError("Impossível armazenar objeto igual a null");
		}
		
	}

	public void excluir(T o) {
		// TODO Auto-generated method stub
		
		try{
			objetoDao.excluir(o.getId());
			MessageJsf.messageInfo("Registro excluído com sucesso");
		}catch(Exception e){
			e.printStackTrace();
			MessageJsf.messageError(e.getMessage());
		}
	}

	public T preRenderView(T o) {
		// TODO Auto-generated method stub
		if(o.getId() != null){
			o = objetoDao.getById(o, classTipo);
		}
		return o;
	}

}
