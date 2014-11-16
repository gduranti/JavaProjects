package br.unisinos.unitunes.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.MediaFilter;
import br.unisinos.unitunes.model.MyMediaFilter;

@Named
@SessionScoped
public class MyMediaListController extends MediaListController {

	private static final long serialVersionUID = 1L;

	@Override
	protected MediaFilter createModelInstance() {
		MediaFilter model = super.createModelInstance();
		model.setMyMediaFilter(MyMediaFilter.ALL);
		return model;
	}

	@Override
	public List<Media> list() {
		List<Media> list = new ArrayList<Media>(getMediaListFromSession());
		return filter(list);
	}

	private List<Media> getMediaListFromSession() {
		if (getModel().getMyMediaFilter() == MyMediaFilter.FAVORITES) {
			return sessionController.getUser().getFavoritesMedias();
		} else {
			return sessionController.getUser().getPurchasedMedias();
		}
	}

	private List<Media> filter(List<Media> list) {

		MediaFilter filter = getModel();

		for (Iterator<Media> iterator = list.iterator(); iterator.hasNext();) {
			Media media = (Media) iterator.next();
			if (!filter.getTypeFilter().equals(media.getCategory().getType())
					|| (filter.getCategoryFilter() != null && !filter.getCategoryFilter().equals(media.getCategory()))) {
				iterator.remove();
			}
		}

		return list;
	}

	@Override
	public String getViewTitle() {
		return "Minhas Mídias";
	}

}
