package br.unisinos.unitunes.business.movement;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.unisinos.unitunes.infra.impl.GenericFacade;
import br.unisinos.unitunes.model.Movement;
import br.unisinos.unitunes.model.User;
import br.unisinos.unitunes.model.event.BalanceChangedEvent;

@Stateless
public class MovementFacade extends GenericFacade<Movement> {

	@Inject
	private MovementDAO dao;

	@Inject
	private Event<BalanceChangedEvent> balanceChangedEvent;

	@PostConstruct
	public void init() {
		setDao(dao);
	}

	@Override
	public Movement add(Movement movement) {

		movement = super.add(movement);

		Double newBalance = getUserBalance(movement.getUser());
		balanceChangedEvent.fire(new BalanceChangedEvent(movement.getUser(), newBalance));

		return movement;
	}

	public Double getUserBalance(User user) {
		return dao.getUserBalance(user);
	}

}
