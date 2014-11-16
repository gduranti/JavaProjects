package br.unisinos.unitunes.business.movement;

import javax.persistence.Query;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.unisinos.unitunes.infra.impl.GenericDAO;
import br.unisinos.unitunes.model.Movement;
import br.unisinos.unitunes.model.User;

class MovementDAO extends GenericDAO<Movement> {

	public Double getUserBalance(User user) {

		StringBuilder hql = new StringBuilder();
		hql.append(" select sum ( case when m.type = 0 then m.value");
		hql.append("              else - m.value end )");
		hql.append(" from Movement m ");
		hql.append(" where m.user = :user ");

		Query query = getEntityManager().createQuery(hql.toString());
		query.setParameter("user", user);
		Object singleResult = query.getSingleResult();
		return (Double) singleResult;
	}

	@Override
	protected void addRestrictions(Movement example, DetachedCriteria criteria) {
		super.addRestrictions(example, criteria);

		if (example.getUser() != null) {
			criteria.add(Restrictions.eq("user", example.getUser()));
		}
	}

}
