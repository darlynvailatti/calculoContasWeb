package br.edu.unoesc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Comarca;
import br.edu.unoesc.modelo.MeuModelo;

import java.lang.reflect.Field;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Id;
 
@FacesConverter(value="ComarcaEntityConverter")
public class ComarcaEntityConverter implements Converter {
 
 
	
 public Object getAsObject(FacesContext fc, UIComponent component, String string) {
  try {

	  GenericDAO<Comarca> comarcaDao = new GenericDAO<>(Comarca.class);
	  Comarca c = new Comarca();
	  c.setId(Long.valueOf(string));
	  
	  return comarcaDao.getById(c, Comarca.class);
	  
  }catch (NumberFormatException e) {
	  e.printStackTrace();
	  return null;
  	}
 }
 
 public String getAsString(FacesContext fc, UIComponent component, Object object) {
  try {
   Class<? extends Object> clazz = object.getClass();
	   for (Field f : clazz.getDeclaredFields()) {
		    if (f.isAnnotationPresent(Id.class)) {
		    	f.setAccessible(true);
		    	Long id = (Long) f.get(object);
		    	return id.toString();
		    }
	   	}
  	}catch (IllegalArgumentException | IllegalAccessException e) {
  	}
  	return null;
 	}
}


	