package br.unisinos.unitunes.business.movement;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.unisinos.unitunes.infra.impl.GenericFacade;
import br.unisinos.unitunes.model.Movement;
import br.unisinos.unitunes.model.User;

@Stateless
public class MovementFacade extends GenericFacade<Movement> {

	@Inject
	private MovementDAO dao;

	@PostConstruct
	public void init() {
		setDao(dao);
	}

	public Double getUserBalance(User user) {
		return dao.getUserBalance(user);
	}

}
