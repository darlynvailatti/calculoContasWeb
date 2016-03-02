package br.edu.unoesc.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.edu.unoesc.controller.generic.GenericBean;
import br.edu.unoesc.controller.generic.GenericBeanCrud;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Advogado;
import br.edu.unoesc.modelo.Escritorio;
import br.edu.unoesc.util.MessageJsf;

@ManagedBean
@ViewScoped
@EqualsAndHashCode(callSuper=false)
public @Data class EscritorioBean implements Serializable, GenericBeanCrud<Escritorio>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8047115181528498663L;
	private GenericBean<Escritorio> generic;
	
	private Escritorio escritorio;
	private List<Escritorio> escritorios;	
	private List<Escritorio> escritoriosFiltrados;

	private Advogado advogado;
	private advogadoBean advogadoBean;
	
	public EscritorioBean() throws InstantiationException, IllegalAccessException{
		generic = new GenericBean<Escritorio>(Escritorio.class);
		escritorio = new Escritorio();
		escritorios = generic.getObjetoDao().listar();
	}
	
	@Override
	public void salvar(){
		generic.salvar(escritorio);
	}
	
	@Override
	public void excluir(Escritorio escritorio) {
		generic.excluir(escritorio);
		escritorios.remove(escritorio);
	}
	
	public String salvarEsair(){
		salvar();
		return "/pessoa/escritorioList.xhtml?faces-redirect=true";
	}
	
	@Override
	public void preRenderView(){
		escritorio = generic.preRenderView(escritorio);
	}
	
	
	public List<Advogado> autoCompleteAdvogado(String query) throws InstantiationException, IllegalAccessException{
		if(advogadoBean == null){
			advogadoBean = new advogadoBean();
		}
		List<Advogado> results = new ArrayList<>();
		for(Advogado a: advogadoBean.getAdvogados()){
			if(a.getCnpj().toString().toLowerCase().contains(query.toLowerCase()) ||
			   a.getCpf().toLowerCase().contains(query.toLowerCase()) ||
			   a.getNome().toLowerCase().contains(query.toLowerCase()) ||
			   a.getRazaoSocial().toLowerCase().contains(query.toLowerCase())){
				results.add(a);
			}
		}
		return results;
	}
	
	public void removeAdvogado(Advogado advogado){
		try{
			escritorio.getAdvogados().remove(advogado);
			generic.getObjetoDao().salvar(escritorio);
			MessageJsf.messageInfo("Advogado " + advogado.getId() + " removido com sucesso");
		}catch(SQLException e){
			MessageJsf.messageError("Ocorreu um erro");
			e.printStackTrace();
		}
	}
	
	public void adicionarAdovgadoParaEscritorio(){
		if(!escritorio.getAdvogados().contains(advogado)){
			escritorio.getAdvogados().add(this.advogado);
			try {
				generic.getObjetoDao().salvar(escritorio);
			} catch (SQLException e) {
				MessageJsf.messageError("Ocorreu um erro");
				e.printStackTrace();
			}
		}else{
			MessageJsf.messageError("Advogado " + advogado.getId() + 
									" já relacionado ao escritório " +
									escritorio.getId());
		}
	}
	
}
