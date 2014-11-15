package br.unisinos.unitunes.infra.impl;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import br.unisinos.unitunes.infra.Controller;
import br.unisinos.unitunes.infra.Facade;
import br.unisinos.unitunes.infra.Model;
import br.unisinos.unitunes.infra.exception.ApplicationException;

public abstract class GenericController<T extends Model> implements Controller<T>, Serializable {

	private static final long serialVersionUID = 1L;

	Facade<T> facade;

	private T model;

	@PostConstruct
	public void initModel() {
		// resetModel();
	}

	@Override
	public void init() {
	}

	@Override
	public void setFacade(Facade<T> facade) {
		this.facade = facade;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <F extends Facade<T>> F getFacade() {
		return (F) facade;
	}

	@Override
	public T getModel() {
		return model;
	}

	@Override
	public void setModel(T model) {
		this.model = model;
	}

	protected void resetModel() {
		model = createModelInstance();
	}

	protected T createModelInstance() {
		try {
			return getModelClass().newInstance();
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	Class<T> getModelClass() {
		return facade.getModelClass();
	}

	@Override
	public String close() {
		return "index?faces-redirect=true";
	}

	@Override
	public String getViewTitle() {
		return null;
	}

}
