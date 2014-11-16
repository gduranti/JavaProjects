package br.unisinos.unitunes.controller;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.User;
import br.unisinos.unitunes.model.event.BalanceChangedEvent;
import br.unisinos.unitunes.model.event.UserMediaChangedEvent;

@Named
@SessionScoped
public class SessionController implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;
	private Calendar logginDate;
	private Double balance;

	public User getUser() {
		return user;
	}

	public void login(User user) {
		this.user = user;
		this.logginDate = Calendar.getInstance();
	}

	public void logoff() {
		this.user = null;
		this.logginDate = null;
		this.balance = null;
	}

	public Calendar getLoginDate() {
		return logginDate;
	}

	public boolean isLoged() {
		return user != null;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public boolean isUserPurchasedMedia(Media media) {
		return user.getPurchasedMedias().contains(media);
	}

	public boolean isUserPublishedMedia(Media media) {
		return media.getAuthor().equals(user);
	}

	public boolean isUserFavoriteMedia(Media media) {
		return user.getFavoritesMedias().contains(media);
	}

	public void observeUserMedia(@Observes UserMediaChangedEvent userMediaChangedEvent) {
		if (userMediaChangedEvent.getUser().equals(user)) {
			user = userMediaChangedEvent.getUser();
		}
	}

	public void observeBalance(@Observes BalanceChangedEvent balanceChangedEvent) {
		if (balanceChangedEvent.getUser().equals(user)) {
			balance = balanceChangedEvent.getNewBalance();
		}
	}

}
