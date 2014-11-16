package br.unisinos.unitunes.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.event.FileUploadEvent;

import br.unisinos.unitunes.business.media.MediaFacade;
import br.unisinos.unitunes.infra.faces.FacesUtil;
import br.unisinos.unitunes.infra.impl.FormController;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.MediaContent;
import br.unisinos.unitunes.model.MediaType;

@Named
@ViewAccessScoped
public class MediaFormController extends FormController<Media> {

	private static final long serialVersionUID = 1L;

	@Inject
	private MediaFacade facade;

	@Inject
	private SessionController sessionController;

	private MediaType mediaType;
	private String thumbFileName;

	@PostConstruct
	public void initFacade() {
		setFacade(facade);
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public String getThumbFileName() {
		return thumbFileName;
	}

	@Override
	public void setModel(Media model) {
		super.setModel(model);
		mediaType = model.getCategory().getType();
	}

	@Override
	protected Media createModelInstance() {
		Media media = new Media();
		media.setAuthor(sessionController.getUser());
		return media;
	}

	public void handleFileUpload(FileUploadEvent event) {
		MediaContent mediaContent = new MediaContent();
		mediaContent.setContent(event.getFile().getContents());
		getModel().setContent(mediaContent);
		getModel().setFileName(event.getFile().getFileName());
	}

	public void handleThumbUpload(FileUploadEvent event) {
		getModel().setThumb(event.getFile().getContents());
		thumbFileName = event.getFile().getFileName();
	}

	@Override
	public void save() {

		boolean isInclusion = isInclusion();

		super.save();

		if (isInclusion) {
			FacesUtil.addInfoMessage("Mídia incluída com sucesso.");
		} else {
			FacesUtil.addInfoMessage("Mídia alterada com sucesso.");
		}
	}

	@Override
	public String getViewTitle() {
		return isInclusion() ? "Nova Mídia" : "Mídia";
	}

	@Override
	public String close() {
		return "media-list?faces-redirect=true";
	}

}
