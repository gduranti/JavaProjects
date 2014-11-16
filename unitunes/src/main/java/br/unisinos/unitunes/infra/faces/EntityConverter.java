package br.unisinos.unitunes.infra.faces;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unisinos.unitunes.infra.impl.GenericModel;

@FacesConverter(forClass = GenericModel.class, value = "entityConverter")
public class EntityConverter implements Converter {

	private static final String CONVERTER_KEY = "com.gcd.debub.web.EntityConverter#Map";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String entityId) {
		if (entityId == null || entityId.isEmpty()) {
			return null;
		} else {
			return getEntityMap(context).get(entityId);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object entity) {
		if (entity instanceof GenericModel) {
			GenericModel model = (GenericModel) entity;

			String entityId = String.format("%s#%s", entity.getClass().getSimpleName(), model.getId());

			getEntityMap(context).put(entityId, model);

			return entityId;
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private Map<String, GenericModel> getEntityMap(FacesContext context) {
		Map<String, GenericModel> map;
		map = (Map<String, GenericModel>) context.getViewRoot().getViewMap().get(CONVERTER_KEY);
		if (map == null) {
			map = new HashMap<>();
			context.getViewRoot().getViewMap().put(CONVERTER_KEY, map);
		}
		return map;
	}

}
