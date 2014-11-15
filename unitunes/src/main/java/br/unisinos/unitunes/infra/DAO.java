package br.unisinos.unitunes.infra;

import java.util.List;

public interface DAO<T extends Model> {

	T find(Long id);

	void add(T entity);

	void update(T entity);

	void remove(T entity);

	long count(T example);

	List<T> list(T example);

}