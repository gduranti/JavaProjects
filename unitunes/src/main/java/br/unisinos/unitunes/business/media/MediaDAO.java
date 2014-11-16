package br.unisinos.unitunes.business.media;

import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.unisinos.unitunes.infra.impl.GenericDAO;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.MediaFilter;
import br.unisinos.unitunes.model.MediaSummaryDTO;
import br.unisinos.unitunes.model.MovementSource;

class MediaDAO extends GenericDAO<Media> {

	@Override
	public void add(Media media) {
		super.add(media.getContent());
		super.add(media);
	}

	@Override
	protected void addRestrictions(Media example, DetachedCriteria criteria) {
		super.addRestrictions(example, criteria);

		if (example instanceof MediaFilter) {
			MediaFilter mediaFilter = (MediaFilter) example;

			if (mediaFilter.getCategoryFilter() != null) {
				criteria.add(Restrictions.eq("category", mediaFilter.getCategoryFilter()));
			}
			if (mediaFilter.getTypeFilter() != null) {
				criteria.createCriteria("category").add(Restrictions.eq("type", mediaFilter.getTypeFilter()));
			}
		}
	}

	public List<MediaSummaryDTO> listSummary(Calendar initialDate, Calendar finalDate) {

		StringBuilder hql = new StringBuilder();
		hql.append(" select new MediaSummaryDTO(med.id, med.name, count(mov.id), sum(mov.value)) ");
		hql.append(" from Movement mov ");
		hql.append(" inner join mov.media med ");
		hql.append(" where mov.source = :movementSource ");
		if (initialDate != null) {
			hql.append(" and med.inclusionDate >= :initialDate ");
		}
		if (finalDate != null) {
			hql.append(" and med.inclusionDate <= :finalDate ");
		}
		hql.append(" group by med.id, med.name ");

		TypedQuery<MediaSummaryDTO> query = getEntityManager().createQuery(hql.toString(), MediaSummaryDTO.class);
		query.setParameter("movementSource", MovementSource.PURCHASED_MEDIA);
		if (initialDate != null) {
			initialDate.set(Calendar.HOUR_OF_DAY, 0);
			initialDate.set(Calendar.MINUTE, 0);
			initialDate.set(Calendar.SECOND, 0);
			query.setParameter("initialDate", initialDate);
		}
		if (finalDate != null) {
			finalDate.set(Calendar.HOUR_OF_DAY, 23);
			finalDate.set(Calendar.MINUTE, 59);
			finalDate.set(Calendar.SECOND, 59);
			query.setParameter("finalDate", finalDate);
		}
		return query.getResultList();
	}

}
