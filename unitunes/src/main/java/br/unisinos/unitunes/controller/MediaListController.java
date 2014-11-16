package br.unisinos.unitunes.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.unisinos.unitunes.business.album.AlbumFacade;
import br.unisinos.unitunes.business.media.MediaFacade;
import br.unisinos.unitunes.infra.impl.ListController;
import br.unisinos.unitunes.model.Album;
import br.unisinos.unitunes.model.Media;
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
	private AlbumFacade albumFacade;

	private List<Album> albuns;

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

	@Override
	public void clearList() {
		super.clearList();
		albuns = null;
	}

	public List<Album> getAlbuns() {
		if (getModel().getTypeFilter() != MediaType.MUSIC) {
			return new ArrayList<>();
		}
		if (albuns == null) {
			albuns = albumFacade.list(new Album());
		}
		return albuns;
	}

	public StreamedContent getThumb() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();

		} else {
			Long id = Long.valueOf(context.getExternalContext().getRequestParameterMap().get("media-id"));
			for (Media media : list()) {
				if (media.getId().equals(id) && media.getThumb() != null) {
					return new DefaultStreamedContent(new ByteArrayInputStream(media.getThumb()));
				}
			}
			return new DefaultStreamedContent();
		}
	}

	public StreamedContent getAlbumThumb() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();

		} else {
			Long id = Long.valueOf(context.getExternalContext().getRequestParameterMap().get("album-id"));
			for (Album album : getAlbuns()) {
				if (album.getId().equals(id)) {
					return new DefaultStreamedContent(new ByteArrayInputStream(album.getThumb()));
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
