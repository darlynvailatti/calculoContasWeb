package br.edu.unoesc.controller;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.edu.unoesc.dao.UsuarioDAO;
import br.edu.unoesc.modelo.Usuario;
import br.edu.unoesc.util.MessageJsf;
import lombok.Data;

@ManagedBean(name = "login")
@SessionScoped
public @Data class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

//  funcionais
    private Usuario usuario = new Usuario();
    
//    nao funcionais
    private Logger logger;


    public void preRenderView(){
    	this.usuario = new Usuario();
    	logger = Logger.getLogger(this.getClass().getName());
    }

    //validate login
    public String validateUsernamePassword() throws NoSuchAlgorithmException {

    	UsuarioDAO usuarioDao = new UsuarioDAO(Usuario.class);
    	
    	Usuario u = usuarioDao.getByLoginAndPassword(usuario);
    	usuarioDao.fechar();
    	
    	logger.info("Tentativa de login: " + usuario.getLogin());
    	if(u != null) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("usuario", usuario);
            logger.info("Login realizado com sucesso:" + usuario.getLogin());
            return "/template.xhtml?faces-redirect=true";
        } else {
            MessageJsf.messageError("Usuário ou senha inválidos");
            logger.warning("Falha no login: " + usuario.getLogin());;
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        if(logger == null){
        	logger = Logger.getLogger(this.getClass().getName());
        }
        
    	logger.info("Tentativa de logou: " + usuario.getLogin());
        if(usuario != null){
        	HttpSession session = SessionBean.getSession();
        	session.invalidate();
        	logger.info("Logou realizado com sucesso: " + usuario.getLogin());
        }else{
        	logger.info("Falha ao realizar logout");
        }
        return "/login.xhtml?faces-redirect=true";
    }
    
}