package br.unisinos.unitunes.business.media;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.unisinos.unitunes.infra.exception.BusinessException;
import br.unisinos.unitunes.infra.impl.GenericFacade;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.event.MediaAddedEvent;

@Stateless
public class MediaFacade extends GenericFacade<Media> {

	@Inject
	private MediaDAO dao;

	@Inject
	private Event<MediaAddedEvent> mediaAddedEvent;

	@PostConstruct
	public void init() {
		setDao(dao);
	}

	@Override
	public Media add(Media media) {
		media = super.add(media);
		mediaAddedEvent.fire(new MediaAddedEvent(media));
		return media;
	}

	@Override
	protected void validate(Media media) {
		if (media.getContent() == null || media.getContent().length == 0) {
			throw new BusinessException("Deve ser selecionado um arquivo de conteúdo para a mídia.");
		}

		if (media.getType().hasDuration() && (media.getDuration() == null || media.getDuration() == 0)) {
			throw new BusinessException("Deve ser informada a duração da mídia.");
		}
	}

}
