package br.unisinos.unitunes.infra;

import java.util.List;

public interface Facade<T extends Model> {

	T find(Long id);

	T add(T model);

	T update(T model);

	void remove(Long id);

	void remove(T model);

	Long count(T example);

	List<T> list(T example);

	Class<T> getModelClass();

}