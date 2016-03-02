package br.edu.unoesc.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.edu.unoesc.controller.generic.GenericBean;
import br.edu.unoesc.controller.generic.GenericBeanCrud;
import br.edu.unoesc.modelo.Categoria;
import lombok.Data;

@ManagedBean
public @Data class CategoriaBean implements Serializable, GenericBeanCrud<Categoria>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7726329650627771301L;
	private GenericBean<Categoria> genericBean;
	
	private Categoria categoria;
	private List<Categoria> categorias;
	private List<Categoria> categoriasFiltradas;
	
	public CategoriaBean() throws InstantiationException, IllegalAccessException{
		genericBean = new GenericBean<>(Categoria.class);
		categoria = new Categoria();
		categorias = genericBean.getObjetoDao().listar();
		
	}
	
	
	
	public String salvarEsair(){
		salvar();
		return "/categoria/categoriaList.xhtml?faces-redirect=true";
	}

	@Override
	public void salvar() {
		genericBean.salvar(categoria);
	}



	@Override
	public void excluir(Categoria o) {
		genericBean.excluir(o);
		categorias.remove(o);
	}



	@Override
	public void preRenderView() {
		categoria = genericBean.preRenderView(categoria);
	}
	
}
