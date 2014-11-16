package br.unisinos.unitunes.business.media;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.unisinos.unitunes.infra.impl.GenericFacade;
import br.unisinos.unitunes.model.MediaCategory;
import br.unisinos.unitunes.model.MediaType;

@Stateless
public class MediaCategoryFacade extends GenericFacade<MediaCategory> {

	@Inject
	private MediacategoryDAO dao;

	@PostConstruct
	public void init() {
		setDao(dao);
	}

	public List<MediaCategory> listByType(MediaType type) {
		MediaCategory example = new MediaCategory();
		example.setType(type);
		return super.list(example);
	}

}
