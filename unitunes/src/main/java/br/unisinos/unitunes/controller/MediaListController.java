package br.unisinos.unitunes.controller;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.unisinos.unitunes.business.media.MediaFacade;
import br.unisinos.unitunes.business.user.UserFacade;
import br.unisinos.unitunes.infra.impl.ListController;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.MediaContent;
import br.unisinos.unitunes.model.MediaFilter;
import br.unisinos.unitunes.model.MediaType;
import br.unisinos.unitunes.model.User;
import br.unisinos.unitunes.model.event.MediaChangedEvent;

@Named
@SessionScoped
public class MediaListController extends ListController<Media> {

	private static final long serialVersionUID = 1L;

	@Inject
	protected SessionController sessionController;

	@Inject
	private MediaFacade facade;

	@Inject
	private UserFacade userFacade;

	@PostConstruct
	public void initFacade() {
		setFacade(facade);
	}

	@Override
	protected MediaFilter createModelInstance() {
		MediaFilter model = new MediaFilter();
		model.setTypeFilter(MediaType.BOOK);
		return model;
	}

	@Override
	public MediaFilter getModel() {
		return (MediaFilter) super.getModel();
	}

	@Override
	public String getViewTitle() {
		return "Pesquisar Mídias";
	}

	public void addFavoriteMedia(Media media) {
		userFacade.addFavoriteMedia(media, sessionController.getUser());
	}

	public void removeFavoriteMedia(Media media) {
		userFacade.removeFavoriteMedia(media, sessionController.getUser());
	}

	public void purchaseMedia(Media media) {
		facade.purchaseMedia(media, sessionController.getUser());
	}

	public StreamedContent executeMedia(Media media) {
		MediaContent mediaContent = facade.loadContent(media);
		DefaultStreamedContent streamedContent = new DefaultStreamedContent();
		streamedContent.setStream(new ByteArrayInputStream(mediaContent.getContent()));
		streamedContent.setName(media.getFileName());
		return streamedContent;
	}

	public String formatMediaValue(Media media) {
		return new DecimalFormat("#0.00").format(media.getValue());
	}

	public StreamedContent getThumb() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();

		} else {
			Long id = Long.valueOf(context.getExternalContext().getRequestParameterMap().get("media-id"));
			for (Media media : list()) {
				if (media.getId().equals(id)) {
					return new DefaultStreamedContent(new ByteArrayInputStream(media.getThumb()));
				}
			}
			return new DefaultStreamedContent();
		}
	}

	public void observeMedias(@Observes MediaChangedEvent mediaChangedEvent) {
		User user = mediaChangedEvent.getMedia().getAuthor();
		if (user.equals(sessionController.getUser())) {
			clearList();
		}
	}

}
