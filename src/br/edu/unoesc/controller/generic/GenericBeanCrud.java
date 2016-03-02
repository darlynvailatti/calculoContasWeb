package br.edu.unoesc.controller.generic;

public interface GenericBeanCrud<T> {
	
	public void salvar();
	
	public void excluir(T o);
	
	/**
	 * Responsavel por carregar componentes para edicao de um objeto
	 */
	public void preRenderView();
	
}
