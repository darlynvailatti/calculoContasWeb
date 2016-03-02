package br.edu.unoesc.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import br.edu.unoesc.controller.generic.GenericBean;
import br.edu.unoesc.controller.generic.GenericBeanCrud;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Comarca;
import br.edu.unoesc.modelo.ComarcaVara;
import br.edu.unoesc.modelo.Estados;
import br.edu.unoesc.util.Impressora;
import br.edu.unoesc.util.MessageJsf;
import br.edu.unoesc.util.Relatorio;
import lombok.Data;

@ManagedBean
public @Data class ComarcaVaraBean implements Serializable, GenericBeanCrud<ComarcaVara>{
	
	private GenericBean<ComarcaVara> genericBean;
	private ComarcaVara comarcaVara;
	private List<ComarcaVara> comarcaVaras;
	private Estados[] estados; 
	
	public ComarcaVaraBean() throws InstantiationException, IllegalAccessException{
		genericBean = new GenericBean<>(ComarcaVara.class);
		comarcaVara = new ComarcaVara();
		comarcaVaras = genericBean.getObjetoDao().listar();
		
	}
	
	public String salvarEsair(){
		salvar();
		return "/comarcaVara/comarcaVaraList.xhtml?faces-redirect=true";
	}

	@Override
	public void salvar() {
		genericBean.salvar(comarcaVara);
	}

	@Override
	public void excluir(ComarcaVara o) {
		genericBean.excluir(o);
		comarcaVaras.remove(o);
	}

	@Override
	public void preRenderView() {
		comarcaVara = genericBean.preRenderView(comarcaVara);
	}
	
}
