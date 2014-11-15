package br.unisinos.unitunes.business.movement;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.unisinos.unitunes.infra.impl.GenericDAO;
import br.unisinos.unitunes.model.Movement;
import br.unisinos.unitunes.model.User;

class MovementDAO extends GenericDAO<Movement> {

	public Double getUserBalance(User user) {

		DetachedCriteria criteria = DetachedCriteria.forClass(Movement.class);
		criteria.add(Restrictions.eq("user", user));
		criteria.setProjection(Projections.sum("value"));

		Double balance = findUniqueResultByCriteria(criteria);

		return balance != null ? balance : 0.0;
	}

}
