package br.unisinos.unitunes.model.event;

import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.User;

public class UserMediaChangedEvent {

	private User user;
	private Media media;

	public UserMediaChangedEvent(Media media, User user) {
		this.media = media;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public Media getMedia() {
		return media;
	}

}
