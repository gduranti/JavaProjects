package br.unisinos.unitunes.model.event;

import br.unisinos.unitunes.model.User;

public class UserInfoChangedEvent {

	private User user;

	public UserInfoChangedEvent(User updatedUser) {
		this.user = updatedUser;
	}

	public User getUser() {
		return user;
	}

}
