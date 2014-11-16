package br.unisinos.unitunes.business.media;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.hibernate.Hibernate;

import br.unisinos.unitunes.business.movement.MovementFacade;
import br.unisinos.unitunes.business.user.UserFacade;
import br.unisinos.unitunes.controller.SessionController;
import br.unisinos.unitunes.infra.exception.BusinessException;
import br.unisinos.unitunes.infra.impl.GenericFacade;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.MediaContent;
import br.unisinos.unitunes.model.User;
import br.unisinos.unitunes.model.event.MediaChangedEvent;

@Stateless
public class MediaFacade extends GenericFacade<Media> {

	@Inject
	private MediaDAO dao;

	@Inject
	private UserFacade userFacade;

	@Inject
	private MovementFacade movementFacade;

	@Inject
	private SessionController sessionController;

	@Inject
	private Event<MediaChangedEvent> mediaEvent;

	@PostConstruct
	public void init() {
		setDao(dao);
	}

	@Override
	public Media add(Media media) {
		media = super.add(media);
		mediaEvent.fire(new MediaChangedEvent(media));
		return media;
	}

	@Override
	public Media update(Media media) {
		media = super.update(media);
		mediaEvent.fire(new MediaChangedEvent(media));
		return media;
	}

	public void purchaseMedia(Media media, User user) {
		movementFacade.generateMediaPurchasedMovement(media);
		userFacade.addPurchasedMedia(media, user);
	}

	@Override
	protected void validate(Media media) {
		if (media.getId() == null && (media.getContent() == null || media.getContent().getContent().length == 0)) {
			throw new BusinessException("Deve ser selecionado um arquivo de conteúdo para a mídia.");
		}

		if (media.getCategory().getType().hasDuration() && (media.getDuration() == null || media.getDuration() == 0)) {
			throw new BusinessException("Deve ser informada a duração da mídia.");
		}

		if (media.getId() != null) {
			if (!media.getAuthor().equals(sessionController.getUser()) && sessionController.getUser().isAdmin()) {
				throw new BusinessException("Esta mídia só pode ser alterada pelo seu autor ou por um administrador");
			}
		}
	}

	public MediaContent loadContent(Media media) {
		media = find(media.getId());
		Hibernate.initialize(media.getContent());
		return media.getContent();
	}

}
