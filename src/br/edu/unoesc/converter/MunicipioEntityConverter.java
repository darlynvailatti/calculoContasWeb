package br.edu.unoesc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unoesc.dao.GenericDAO;
import br.edu.unoesc.modelo.Comarca;
import br.edu.unoesc.modelo.MeuModelo;
import br.edu.unoesc.modelo.Municipio;

import java.lang.reflect.Field;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Id;
 
@FacesConverter(value="MunicipioEntityConverter")
public class MunicipioEntityConverter implements Converter {
 
 
	
 public Object getAsObject(FacesContext fc, UIComponent component, String string) {
  try {
	  System.out.println("Converter string " + string );
	  GenericDAO<Municipio> municipioDao = new GenericDAO<>(Municipio.class);
	  Municipio o = new Municipio();
	  o.setId(Long.valueOf(string));
	  
	  return municipioDao.getById(o, Municipio.class);
	  
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


	