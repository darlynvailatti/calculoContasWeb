package br.edu.unoesc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.tomcat.jni.Proc;

import lombok.Data;
import br.edu.unoesc.controller.generic.GenericBean;
import br.edu.unoesc.controller.generic.GenericBeanCrud;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Categoria;
import br.edu.unoesc.modelo.Processo;
import br.edu.unoesc.util.MessageJsf;

@ManagedBean
@ViewScoped
public @Data class ProcessoBean implements Serializable, GenericBeanCrud<Processo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8047115181528498663L;
	private GenericBean<Processo> genericBean;
	
	private Processo processo;
	private List<Processo> processos;
	private List<Processo> processosFiltrados;
	
	private String numero;
	private String numeroTribunal;
	private String numeroStj;
	
	public ProcessoBean() throws InstantiationException, IllegalAccessException{
		genericBean = new GenericBean<>(Processo.class);
		processo = new Processo();
		processos = genericBean.getObjetoDao().listar();
	}
	

	public String salvarEsair(){
		salvar();
		return "/processo/processoList.xhtml?faces-redirect=true";
	}
	
	
	
	
	public void addNumero(){
		System.out.println(numero);
		this.processo.getNumeros().add(numero);
	}
	
	public void addNumeroTribunal(){
		this.processo.getNumeroTribunal().add(numeroTribunal);
	}
	
	public void addNumeroStj(){
		this.processo.getNumeroStj().add(numeroStj);
	}


	@Override
	public void salvar() {
		genericBean.salvar(processo);
	}


	@Override
	public void excluir(Processo o) {
		genericBean.excluir(o);
		processos.remove(o);
	}


	@Override
	public void preRenderView() {
		processo = genericBean.preRenderView(processo);
	}
	
//	Específicos
	
	public List<Categoria> categoriaAutoComplete(String query){
		GenericDAO<Categoria> categoriaDAO = new GenericDAO<>(Categoria.class);
		
		List<Categoria> results = new ArrayList<>();
		for(Categoria c: categoriaDAO.listar()){
			if(c.getDescricao().toLowerCase().contains(query.toLowerCase())){
				results.add(c);
			}
		}
		return results;
	}
	
}
