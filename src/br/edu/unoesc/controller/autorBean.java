package br.edu.unoesc.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import lombok.Data;
import br.edu.unoesc.controller.generic.GenericBean;
import br.edu.unoesc.controller.generic.GenericBeanCrud;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Autor;
import br.edu.unoesc.util.MessageJsf;

@ManagedBean
public @Data class autorBean implements Serializable, GenericBeanCrud<Autor>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8047115181528498663L;
	private GenericBean<Autor> genericBean;
	
	private Autor autor;
	private List<Autor> autors;

	public autorBean() throws InstantiationException, IllegalAccessException{
		genericBean = new GenericBean<Autor>(Autor.class);
		autor = new Autor();
		autors = genericBean.getObjetoDao().listar();
	}
	
	public String salvarEsair(){
		salvar();
		return "/pessoa/autorList.xhtml?faces-redirect=true";
	}

	@Override
	public void salvar() {
		genericBean.salvar(autor);
		
	}

	@Override
	public void excluir(Autor o) {
		genericBean.excluir(o);
		autors.remove(o);
	}

	@Override
	public void preRenderView() {
		autor = genericBean.preRenderView(autor);
	}
	
	
}
