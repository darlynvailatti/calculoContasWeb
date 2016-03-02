package br.edu.unoesc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;

import br.edu.unoesc.controller.generic.GenericBean;
import br.edu.unoesc.controller.generic.GenericBeanCrud;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Comarca;
import br.edu.unoesc.modelo.ComarcaVara;
import br.edu.unoesc.modelo.Estados;
import br.edu.unoesc.modelo.Municipio;
import br.edu.unoesc.util.MessageJsf;
import lombok.Data;

@ManagedBean
@javax.faces.bean.ViewScoped
public @Data class ComarcaBean implements Serializable, GenericBeanCrud<Comarca>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8047115181528498663L;
	private GenericBean<Comarca> genericBean;
	
	private Comarca comarca;
	private List<Comarca> comarcas;
	private List<Comarca> comarcasFiltradas;
	private Estados[] estados; 
	
	private List<ComarcaVara> comarcaVaras;
	private GenericDAO<ComarcaVara> comarcaVaraDao;
	
	private GenericDAO<Municipio> municipioDao;

	public ComarcaBean() throws InstantiationException, IllegalAccessException{
		genericBean = new GenericBean<>(Comarca.class);
		comarca = new Comarca();
		comarcas = genericBean.getObjetoDao().listar();
		
		estados = Estados.values();
		
		comarcaVaraDao = new GenericDAO<>(ComarcaVara.class);
		comarcaVaras = comarcaVaraDao.listar();
		
		municipioDao = new GenericDAO<>(Municipio.class);
		
	}

	@Override
	public void salvar() {
		genericBean.salvar(comarca);
	}

	@Override
	public void excluir(Comarca o) {
		genericBean.excluir(o);
		comarcas.remove(o);
	}

	@Override
	public void preRenderView() {
		comarca = genericBean.preRenderView(comarca);
	}
	
	public String salvarEsair(){
		salvar();
		return "/comarca/comarcaList.xhtml?faces-redirect=true";
	}
	
	
	
	public void excluirComarcaVara(ComarcaVara comarcaVara){
		try{
			comarca.getComarcaVaras().remove(comarcaVara);
			
			genericBean.getObjetoDao().salvar(comarca);
			
			MessageJsf.messageInfo("Registro excluído com sucesso");
		}catch(Exception e){
			MessageJsf.messageError(e.getMessage());
		}
	
	}
	
	public List<Municipio> municipioAutoComplete(String query){
		List<Municipio> results = new ArrayList<>();
		
		for(Municipio m: municipioDao.listar()){
			if(m.getNome().toLowerCase().contains(query.toLowerCase())){
				results.add(m);
			}
		}
		
		return results;
	}
}
