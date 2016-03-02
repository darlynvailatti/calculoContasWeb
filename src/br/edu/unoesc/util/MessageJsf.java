package br.edu.unoesc.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageJsf {
	private static FacesContext fc;
	
	
	private static void getCurrentContext(){
		fc = FacesContext.getCurrentInstance();
	}
	
	public static void messageInfo(String message){
		getCurrentContext();
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,message,null);
		fc.addMessage(null, fm);
	}
	
	public static void messageWarn(String message){
		getCurrentContext();
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN,message,null);
		fc.addMessage(null, fm);
	}
	
	public static void messageError(String message){
		getCurrentContext();
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,message,null);
		fc.addMessage(null, fm);
	}
	
	public static void messageFatal(String message){
		getCurrentContext();
		FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_FATAL,message,null);
		fc.addMessage(null, fm);
	}
}
