package br.unisinos.unitunes.infra.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import br.unisinos.unitunes.infra.DAO;
import br.unisinos.unitunes.infra.Facade;
import br.unisinos.unitunes.infra.Model;

public abstract class GenericFacade<T extends Model> implements Facade<T> {

	protected DAO<T> dao;

	public void setDao(DAO<T> dao) {
		this.dao = dao;
	}

	@Override
	public T find(Long id) {
		return dao.find(id);
	}

	@Override
	public T add(T model) {
		validate(model);
		dao.add(model);
		return model;
	}

	@Override
	public T update(T model) {
		validate(model);
		dao.update(model);
		return model;
	}

	protected void validate(T model) {
	};

	@Override
	public void remove(Long id) {
		// Removes by ID to certificate the model isn't a detached instance
		T model = find(id);
		remove(model);
	}

	@Override
	public void remove(T model) {
		dao.remove(model);
	}

	@Override
	public List<T> list(T example) {
		return dao.list(example);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<T> getModelClass() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

}
