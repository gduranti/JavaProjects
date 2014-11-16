package br.unisinos.unitunes.infra.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import br.unisinos.unitunes.infra.Model;

public abstract class ListController<T extends Model> extends GenericController<T> {

	private static final long serialVersionUID = 1L;

	private List<T> list;

	@PostConstruct
	private void initModel() {
		if (getModel() == null) {
			resetModel();
		}
	}

	public List<T> list() {
		if (list == null) {
			this.list = internalList(getModel());
		}
		return list;
	}

	public void clearList() {
		list = null;
	}

	protected List<T> internalList(T example) {
		return facade.list(example);
	}

	public void remove(Long id) {
		getFacade().remove(id);
		clearList();
	}

}
