package br.edu.unoesc.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.unoesc.controller.generic.GenericBean;
import br.edu.unoesc.controller.generic.GenericBeanCrud;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.dao.MetodologiaDAO;
import br.edu.unoesc.modelo.Metodologia;
import br.edu.unoesc.util.Impressora;
import br.edu.unoesc.util.MessageJsf;
import br.edu.unoesc.util.Relatorio;
import lombok.Data;

@ManagedBean
public @Data class MetodologiaBean implements Serializable, GenericBeanCrud<Metodologia>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7726329650627771301L;
	private GenericBean<Metodologia> genericBean;
	
	private Metodologia metodologia;
	private List<Metodologia> metodologias;
	
	public MetodologiaBean() throws InstantiationException, IllegalAccessException{
		genericBean = new GenericBean<>(Metodologia.class);
		metodologia = new Metodologia();
		metodologias = genericBean.getObjetoDao().listar();
		
	}
	
	
	
	public String salvarEsair(){
		salvar();
		return "/metodologia/metodologiaList.xhtml?faces-redirect=true";
	}
	
	public void exportMetodologiaToPdf(){
		try{
			byte[] report = Relatorio.gerarPdf("sample_1.jrxml", "teste");
			Impressora.pdfView(report, "teste.pdf");
		}catch(Exception e){
			MessageJsf.messageError(e.getMessage());
		}
	}



	@Override
	public void salvar() {
		genericBean.salvar(metodologia);
	}



	@Override
	public void excluir(Metodologia o) {
		genericBean.excluir(o);
		metodologias.remove(o);
	}



	@Override
	public void preRenderView() {
		metodologia = genericBean.preRenderView(metodologia);
	}
	
}
