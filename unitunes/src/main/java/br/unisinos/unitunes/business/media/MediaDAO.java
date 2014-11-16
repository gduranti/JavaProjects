package br.unisinos.unitunes.business.media;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import br.unisinos.unitunes.infra.impl.GenericDAO;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.MediaFilter;

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

}
