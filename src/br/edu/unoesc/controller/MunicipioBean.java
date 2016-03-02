package br.edu.unoesc.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unoesc.controller.generic.GenericBean;
import br.edu.unoesc.controller.generic.GenericBeanCrud;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Estados;
import br.edu.unoesc.modelo.Municipio;
import br.edu.unoesc.util.MessageJsf;
import lombok.Data;

@ManagedBean
@ViewScoped
public @Data class MunicipioBean implements Serializable, GenericBeanCrud<Municipio>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1922165144872684491L;
	private GenericBean<Municipio> genericBean;
	
	private Municipio municipio;
	private List<Municipio> municipios;
	private Estados[] estados;
	
	public MunicipioBean() throws InstantiationException, IllegalAccessException{
		genericBean = new GenericBean<>(Municipio.class);
		municipio = new Municipio();
		municipios = genericBean.getObjetoDao().listar();
		estados = Estados.values();
	}
	

	
	public String salvarEsair(){
		salvar();
		return "/municipio/municipioList.xhtml?faces-redirect=true";
	}



	@Override
	public void salvar() {
		genericBean.salvar(municipio);
	}



	@Override
	public void excluir(Municipio o) {
		genericBean.excluir(o);
		municipios.remove(o);
	}



	@Override
	public void preRenderView() {
		municipio = genericBean.preRenderView(municipio);
	}
	
	
}
