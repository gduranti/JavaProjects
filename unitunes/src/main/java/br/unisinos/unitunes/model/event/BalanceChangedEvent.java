package br.unisinos.unitunes.model.event;

import br.unisinos.unitunes.model.User;

public class BalanceChangedEvent {

	private Double newBalance;
	private User user;

	public BalanceChangedEvent(User user, Double newBalance) {
		this.user = user;
		this.newBalance = newBalance;
	}

	public User getUser() {
		return user;
	}

	public Double getNewBalance() {
		return newBalance;
	}

}
