package br.unisinos.unitunes.model.event;

import br.unisinos.unitunes.model.Media;

public class MediaAddedEvent {

	private Media media;

	public MediaAddedEvent(Media media) {
		this.media = media;
	}

	public Media getMedia() {
		return media;
	}

}
