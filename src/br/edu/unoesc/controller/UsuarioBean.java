package br.edu.unoesc.controller;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import lombok.Data;
import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.dao.UsuarioDAO;
import br.edu.unoesc.modelo.Usuario;
import br.edu.unoesc.util.Criptografia;
import br.edu.unoesc.util.MessageJsf;

@ManagedBean
public @Data class UsuarioBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8047115181528498663L;
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	private UsuarioDAO usuarioDao;		

	private String passwordConfirmar;
	
	private Boolean modoEdicao;
	
	@ManagedProperty("#{login}")
	private Login login;
	
	public UsuarioBean(){
		usuario = new Usuario();
		usuarioDao = new UsuarioDAO(Usuario.class);
		usuarios = usuarioDao.listar();
		modoEdicao = false;
	}
	
	public void salvar() throws NoSuchAlgorithmException{
		try{
			usuarioDao.salvar(usuario);
			MessageJsf.messageInfo("Registro salvo com sucesso");
		}catch(Exception e){
			e.printStackTrace();
			MessageJsf.messageError(e.getMessage());
		}
	}
	
	public String salvarEsair() throws NoSuchAlgorithmException{
		salvar();
		return "/pessoa/usuarioList.xhtml?faces-redirect=true";
	}
	
	public void excluir(Usuario usuario){
		if(usuarioLogadoDiferenteDe(usuario)){
			try{
				usuarioDao.excluir(usuario.getLogin());
				usuarios.remove(usuario);
				MessageJsf.messageInfo("Registro excluído com sucesso");
			}catch(Exception e){
				MessageJsf.messageError(e.getMessage());
			}
		}
	}
	
	
	public void preRenderView(){
		if(usuario.getLogin() != null){
			usuario = usuarioDao.getByLogin(usuario);
		}
	}
	
	private Boolean senhaConfirmacaoIgualSenhaUsuario() throws NoSuchAlgorithmException{
		this.passwordConfirmar = Criptografia.convertStringToMD5(this.passwordConfirmar);
		if(this.passwordConfirmar.equals(usuario.getPassword())){
			return true;
		}
		MessageJsf.messageError("Senha de confirmação não é igual senha informada");
		return false;
	}
	
	public Boolean usuarioNaoCadastrado(){
		if(this.usuarioDao.getByLogin(usuario) == null){
			return true;
		}
		MessageJsf.messageError("Usuário " + usuario.getLogin() + " já cadastrado");
		return false;
	}
	

	public void validarUsuarioESalvar() throws NoSuchAlgorithmException{
		if(modoEdicao){
			if(senhaConfirmacaoIgualSenhaUsuario()){
				salvar();
			}
		}else if(usuarioNaoCadastrado() && senhaConfirmacaoIgualSenhaUsuario()){
			salvar();
		}
	}
	
	public Boolean usuarioLogadoDiferenteDe(Usuario u){
		if(!u.getLogin().equals(login.getUsuario().getLogin())){
			return true;
		}
		MessageJsf.messageError("Você não pode excluir seu próprio usuário");
		return false;
	}
	
}
