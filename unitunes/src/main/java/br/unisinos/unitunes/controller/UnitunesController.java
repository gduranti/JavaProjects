package br.unisinos.unitunes.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.unisinos.unitunes.business.user.UserFacade;
import br.unisinos.unitunes.model.User;

@ApplicationScoped
public class UnitunesController {

	private static final Long ADMIN_ID = 1L;

	private User admin;

	@Inject
	private UserFacade userFacade;

	public User getAdmin() {
		if (admin == null) {
			admin = userFacade.find(ADMIN_ID);
		}
		return admin;
	}

}
