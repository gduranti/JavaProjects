package br.unisinos.unitunes.infra;

import java.util.List;

public interface Facade<T extends Model> {

	T find(Long id);

	T add(T model);

	T update(T model);

	void remove(Long id);
	
	void remove(T model);

	List<T> list(T example);

	Class<T> getModelClass();

}