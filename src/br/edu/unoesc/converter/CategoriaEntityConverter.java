package br.edu.unoesc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Comarca;
import br.edu.unoesc.modelo.MeuModelo;
import br.edu.unoesc.modelo.Categoria;

import java.lang.reflect.Field;

import javax.persistence.Id;
 
@FacesConverter(value="CategoriaEntityConverter")
public class CategoriaEntityConverter implements Converter {
 
 
	
 public Object getAsObject(FacesContext fc, UIComponent component, String string) {
  try {
	  GenericDAO<Categoria> municipioDao = new GenericDAO<>(Categoria.class);
	  Categoria o = new Categoria();
	  o.setId(Long.valueOf(string));
	  
	  return municipioDao.getById(o, Categoria.class);
	  
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


	