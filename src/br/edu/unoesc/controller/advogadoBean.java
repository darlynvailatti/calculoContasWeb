package br.edu.unoesc.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.edu.unoesc.controller.generic.GenericBean;
import br.edu.unoesc.controller.generic.GenericBeanCrud;
import br.edu.unoesc.modelo.Advogado;
import br.edu.unoesc.modelo.Estados;

@ManagedBean(name="advogadoBean")
@EqualsAndHashCode(callSuper=false)
public @Data class advogadoBean implements Serializable, GenericBeanCrud<Advogado> {
	/** 
	 * 
	 */
	private static final long serialVersionUID = 8047115181528498663L;
	private GenericBean<Advogado> genericBean;
	
	private Advogado advogado;
	private List<Advogado> advogados;
	private List<Advogado> advogadosFiltrados;
	private Estados[] estados;
	
	public advogadoBean() throws InstantiationException, IllegalAccessException{
		genericBean = new GenericBean<Advogado>(Advogado.class);
		advogado = new Advogado();
		advogados = genericBean.getObjetoDao().listar();
		estados = Estados.values();
	}
	
	
	@Override
	public void preRenderView() {
		advogado = genericBean.preRenderView(advogado);
	}
	
	public String salvarEsair(){
		salvar();
		return "/pessoa/advogadoList.xhtml?faces-redirect=true";
	}


	@Override
	public void salvar() {
		genericBean.salvar(advogado);
	}


	@Override
	public void excluir(Advogado o) {
		genericBean.excluir(o);
		advogados.remove(o);
	}

}
