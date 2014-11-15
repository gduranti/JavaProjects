package br.unisinos.unitunes.infra.impl;

import javax.faces.context.FacesContext;

import br.unisinos.unitunes.infra.Model;

public abstract class FormController<T extends Model> extends GenericController<T> {

	private static final long serialVersionUID = 1L;

	private Long id;

	@Override
	public void init() {
		if (!FacesContext.getCurrentInstance().isPostback()) {
			if (id != null) {
				T model = load();
				setModel(model);
			} else {
				resetModel();
			}
		}
	}

	protected T load() {
		return facade.find(id);
	}

	public boolean isInclusion() {
		return getId() == null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id != 0) {
			this.id = id;
		}
	}

	public String saveAndClose() {
		save();
		return close();
	}

	public void save() {
		if (isInclusion()) {
			facade.add(getModel());
			// resetModel();
		} else {
			facade.update(getModel());
		}
	}

	public String remove() {
		if (!isInclusion()) {
			facade.remove(getId());
		}
		return close();
	}

}
