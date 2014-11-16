package br.unisinos.unitunes.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.unisinos.unitunes.business.media.MediaFacade;
import br.unisinos.unitunes.business.user.UserFacade;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.MediaContent;

@Named
public class MediaHelper implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionController sessionController;

	@Inject
	private MediaFacade mediaFacade;

	@Inject
	private UserFacade userFacade;

	public void addFavoriteMedia(Media media) {
		userFacade.addFavoriteMedia(media, sessionController.getUser());
	}

	public void removeFavoriteMedia(Media media) {
		userFacade.removeFavoriteMedia(media, sessionController.getUser());
	}

	public void purchaseMedia(Media media) {
		mediaFacade.purchaseMedia(media, sessionController.getUser());
		PurchaseReceiptMessager.showReceipt(media, sessionController.getUser());
	}

	public StreamedContent executeMedia(Media media) {
		MediaContent mediaContent = mediaFacade.loadContent(media);
		DefaultStreamedContent streamedContent = new DefaultStreamedContent();
		streamedContent.setStream(new ByteArrayInputStream(mediaContent.getContent()));
		streamedContent.setName(media.getFileName());
		return streamedContent;
	}

	public String formatMediaValue(Media media) {
		if (media.getValue() == null || media.getValue() == 0.0) {
			return "Gratis";
		} else {
			return new DecimalFormat("#0.00").format(media.getValue());
		}
	}

}
