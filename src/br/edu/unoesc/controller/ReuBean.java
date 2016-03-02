package br.edu.unoesc.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import lombok.Data;
import br.edu.unoesc.controller.generic.GenericBean;
import br.edu.unoesc.controller.generic.GenericBeanCrud;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Reu;
import br.edu.unoesc.util.MessageJsf;

@ManagedBean
public @Data class ReuBean implements Serializable, GenericBeanCrud<Reu>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8047115181528498663L;
	private GenericBean<Reu> genericBean;
	
	private Reu reu;
	private List<Reu> reus;
	private List<Reu> reusFiltrados;
	
	
	public ReuBean() throws InstantiationException, IllegalAccessException {
		genericBean = new GenericBean<>(Reu.class);
		
		reu = new Reu();
		reus = genericBean.getObjetoDao().listar();
	}
	
	
	public String salvarEsair(){
		salvar();
		return "/pessoa/reuList.xhtml?faces-redirect=true";
	}


	@Override
	public void salvar() {
		genericBean.salvar(reu);
	}


	@Override
	public void excluir(Reu o) {
		genericBean.excluir(o);
		reus.remove(o);
	}


	@Override
	public void preRenderView() {
		reu = genericBean.preRenderView(reu);
	}
	
}
