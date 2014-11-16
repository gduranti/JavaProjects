package br.unisinos.unitunes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;

import br.unisinos.unitunes.business.album.AlbumFacade;
import br.unisinos.unitunes.business.media.MediaFacade;
import br.unisinos.unitunes.infra.faces.FacesUtil;
import br.unisinos.unitunes.infra.impl.FormController;
import br.unisinos.unitunes.model.Album;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.MediaFilter;
import br.unisinos.unitunes.model.MediaType;

@Named
@ViewAccessScoped
public class AlbumFormController extends FormController<Album> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MediaFacade mediaFacade;

	@Inject
	private AlbumFacade facade;

	@Inject
	private SessionController sessionController;

	private DualListModel<Media> musicsModel;
	private String thumbFileName;
	private boolean editing;

	@PostConstruct
	public void initFacade() {
		setFacade(facade);
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	protected Album createModelInstance() {
		Album album = new Album();
		album.setAuthor(sessionController.getUser());
		album.setMedias(new ArrayList<Media>());
		return album;
	}

	@Override
	public void setModel(Album model) {
		super.setModel(model);

		List<Media> avaiableMusics = loadAvaiableMusics(model);
		musicsModel = new DualListModel<>(avaiableMusics, model.getMedias());
	}

	private List<Media> loadAvaiableMusics(Album model) {
		MediaFilter example = new MediaFilter();
		example.setAuthor(sessionController.getUser());
		example.setTypeFilter(MediaType.MUSIC);
		List<Media> avaiableMusics = mediaFacade.list(example);

		if (!isInclusion()) {
			avaiableMusics.removeAll(model.getMedias());
		}
		return avaiableMusics;
	}

	public void setMusicsModel(DualListModel<Media> musicsModel) {
		this.musicsModel = musicsModel;
	}

	public DualListModel<Media> getMusicsModel() {
		return musicsModel;
	}

	public String getThumbFileName() {
		return thumbFileName;
	}

	public boolean isEditing() {
		return editing;
	}

	public boolean userCanEdit() {
		return sessionController.getUser().isAdmin() || sessionController.getUser().equals(getModel().getAuthor());
	}

	public void edit() {
		if (!isInclusion() && userCanEdit()) {
			editing = true;
		}
	}

	public void handleThumbUpload(FileUploadEvent event) {
		getModel().setThumb(event.getFile().getContents());
		thumbFileName = event.getFile().getFileName();
	}

	@Override
	public void save() {

		boolean isInclusion = isInclusion();

		getModel().setMedias(musicsModel.getTarget());
		super.save();

		if (isInclusion) {
			FacesUtil.addInfoMessage("Álbum incluído com sucesso.");
		} else {
			FacesUtil.addInfoMessage("Álbum alterado com sucesso.");
		}
	}

	@Override
	public String getViewTitle() {
		return isInclusion() ? "Novo Álbum" : "Álbum " + getModel().getDescription();
	}

	@Override
	public String close() {
		return "media-list?faces-redirect=true";
	}

}
