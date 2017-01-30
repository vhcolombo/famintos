package br.com.victor.famintos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.victor.famintos.model.impl.Restaurante;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
@FacesConverter(value = "entityConverter")
public class EntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (Restaurante) uiComponent.getAttributes().get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		if (value instanceof Restaurante) {
			Restaurante entity = (Restaurante) value;
			if (entity != null && entity instanceof Restaurante && entity.getId() != null) {
				uiComponent.getAttributes().put(entity.getId().toString(), entity);
				return entity.getId().toString();
			}
		}
		return "";
	}
}