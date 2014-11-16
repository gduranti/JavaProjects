package br.unisinos.unitunes.model.event;

import br.unisinos.unitunes.model.Media;

public class MediaChangedEvent {

	private Media media;

	public MediaChangedEvent(Media media) {
		this.media = media;
	}

	public Media getMedia() {
		return media;
	}

}
