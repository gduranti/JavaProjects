package br.unisinos.unitunes.controller;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.unisinos.unitunes.business.media.MediaCategoryFacade;
import br.unisinos.unitunes.model.MediaCategory;
import br.unisinos.unitunes.model.MediaType;
import br.unisinos.unitunes.model.MyMediaFilter;

@Named
public class ListsController {

	@Inject
	private MediaCategoryFacade mediaCategoryFacade;

	public List<MediaType> getMediaTypes() {
		return Arrays.asList(MediaType.values());
	}

	public List<MyMediaFilter> getMyMediaFilterTypes() {
		return Arrays.asList(MyMediaFilter.values());
	}

	public List<MediaCategory> getMediaCategories(MediaType type) {
		return mediaCategoryFacade.listByType(type);
	}

}
