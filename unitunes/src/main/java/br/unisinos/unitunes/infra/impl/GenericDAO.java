package br.unisinos.unitunes.infra.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;

import br.unisinos.unitunes.infra.DAO;
import br.unisinos.unitunes.infra.Model;

@SuppressWarnings("unchecked")
public abstract class GenericDAO<T extends Model> implements DAO<T> {

	@PersistenceContext(unitName = "unitunes")
	EntityManager entityManager;

	@Override
	public T find(Long id) {
		return entityManager.find(getEntityClass(), id);
	}

	@Override
	public void add(T entity) {
		entityManager.persist(entity);
	}

	protected void add(Object entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void remove(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public long count(T example) {
		DetachedCriteria criteria = createCriteriaForExample(example);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.getExecutableCriteria(getSession()).uniqueResult()).longValue();
	}

	@Override
	public List<T> list(T example) {
		DetachedCriteria criteria = createCriteriaForExample(example);
		return listByCriteria(criteria);
	}

	protected <R> List<R> listByCriteria(DetachedCriteria criteria) {
		return criteria.getExecutableCriteria(getSession()).list();
	}

	protected <R> R findUniqueResultByCriteria(DetachedCriteria criteria) {
		return (R) criteria.getExecutableCriteria(getSession()).uniqueResult();
	}

	private DetachedCriteria createCriteriaForExample(T example) {
		DetachedCriteria criteria = createCriteria();
		addRestrictions(example, criteria);
		return criteria;
	}

	protected void addRestrictions(T example, DetachedCriteria criteria) {
		criteria.add(Example.create(example));
	}

	protected DetachedCriteria createCriteria() {
		return DetachedCriteria.forClass(getEntityClass());
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	private Class<T> getEntityClass() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

}