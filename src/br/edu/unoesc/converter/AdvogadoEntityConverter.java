package br.edu.unoesc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Advogado;

import java.lang.reflect.Field;

import javax.persistence.Id;
 
@FacesConverter(value="AdvogadoEntityConverter")
public class AdvogadoEntityConverter implements Converter {
 
 
	
 public Object getAsObject(FacesContext fc, UIComponent component, String string) {
  try {
	  GenericDAO<Advogado> advogadoDao = new GenericDAO<>(Advogado.class);
	  Advogado o = new Advogado();
	  o.setId(Long.valueOf(string));
	  
	  return advogadoDao.getById(o, Advogado.class);
	  
  }catch (NumberFormatException e) {
	  e.printStackTrace();
	  return null;
  	}
 }
 
 public String getAsString(FacesContext fc, UIComponent component, Object object) {
  try {
//	  A classe Advogado herda de Pessoa, sendo assim o atributo Id, está presente na superclasse
//	  Pessoa e não em advogado.
	  boolean encontrouAtributoNaClasse = false;
	  Long id = null;
	  
	  
	  Class<? extends Object> clazz = object.getClass();
	  
//	  	   Percorre todos os atributos da classe em busca do atributo anotado com @Id
		   for (Field f : clazz.getDeclaredFields()) {
			    if (f.isAnnotationPresent(Id.class)) {
			    	f.setAccessible(true);
			    	id = (Long) f.get(object);
			    	encontrouAtributoNaClasse = true;
			    }
		   	}
		   
//		   Se nao encontrar atributo na classe procura na classe Mae/Pai (superClass)
		   if(!encontrouAtributoNaClasse){
			   for (Field f : clazz.getSuperclass().getDeclaredFields()) {
				    if (f.isAnnotationPresent(Id.class)) {
				    	f.setAccessible(true);
				    	id = (Long) f.get(object);
				    	encontrouAtributoNaClasse = true;
				    }
			   	}
		   }
		   
//		   Se nao encontrar
		   if(!encontrouAtributoNaClasse){
			   throw new Exception("Não conseguiu encontrar o atributo Id para fazer a aconversão de string para Objeto");
		   }
		   return id.toString();
		   
  		}catch (Exception e) {
  		e.printStackTrace();
  	}
  	return null;
 	}
}


	