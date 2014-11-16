package br.unisinos.unitunes.business.movement;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.unisinos.unitunes.controller.SessionController;
import br.unisinos.unitunes.controller.UnitunesController;
import br.unisinos.unitunes.infra.impl.GenericFacade;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.Movement;
import br.unisinos.unitunes.model.MovementSource;
import br.unisinos.unitunes.model.MovementType;
import br.unisinos.unitunes.model.PaymentType;
import br.unisinos.unitunes.model.User;
import br.unisinos.unitunes.model.event.BalanceChangedEvent;

@Stateless
public class MovementFacade extends GenericFacade<Movement> {

	@Inject
	private MovementDAO dao;

	@Inject
	private SessionController sessionController;

	@Inject
	private UnitunesController unitunesController;

	@Inject
	private Event<BalanceChangedEvent> balanceChangedEvent;

	@PostConstruct
	public void init() {
		setDao(dao);
	}

	public Movement generateCreditPurchasedMovement(PaymentType paymentType, Double value) {
		Movement movement = new Movement();
		movement.setDate(Calendar.getInstance());
		movement.setDescription("Aquisição de Crédito.");
		movement.setSource(MovementSource.PURCHASED_CREDIT);
		movement.setType(MovementType.CREDIT);
		movement.setUser(sessionController.getUser());
		movement.setPaymentType(paymentType);
		movement.setValue(value);
		return add(movement);
	}

	public Movement generateMediaPurchasedMovement(Media media) {
		Calendar now = Calendar.getInstance();

		Movement creditAuthorMovement = new Movement();
		creditAuthorMovement.setDate(now);
		creditAuthorMovement.setDescription("Venda de mídia");
		creditAuthorMovement.setSource(MovementSource.SOLD_MEDIA);
		creditAuthorMovement.setType(MovementType.CREDIT);
		creditAuthorMovement.setUser(media.getAuthor());
		creditAuthorMovement.setValue(media.getValue() * 0.9);
		add(creditAuthorMovement);

		Movement creditComissionMovement = new Movement();
		creditComissionMovement.setDate(now);
		creditComissionMovement.setDescription("Comissão de venda de mídia");
		creditComissionMovement.setSource(MovementSource.COMMISION);
		creditComissionMovement.setType(MovementType.CREDIT);
		creditComissionMovement.setUser(unitunesController.getAdmin());
		creditComissionMovement.setValue(media.getValue() * 0.1);
		add(creditComissionMovement);

		Movement debitMovement = new Movement();
		debitMovement.setDate(now);
		debitMovement.setDescription("Compra de mídia");
		debitMovement.setSource(MovementSource.PURCHASED_MEDIA);
		debitMovement.setType(MovementType.DEBIT);
		debitMovement.setUser(sessionController.getUser());
		debitMovement.setValue(media.getValue());
		add(debitMovement);

		return debitMovement;
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
