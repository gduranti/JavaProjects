package br.unisinos.unitunes.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.unisinos.unitunes.business.movement.MovementFacade;
import br.unisinos.unitunes.infra.faces.FacesUtil;
import br.unisinos.unitunes.infra.session.SessionController;
import br.unisinos.unitunes.model.Movement;
import br.unisinos.unitunes.model.MovementSource;
import br.unisinos.unitunes.model.MovementType;
import br.unisinos.unitunes.model.PaymentType;

@Named
@ViewAccessScoped
public class CreditPurchaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionController sessionController;

	@Inject
	private MovementFacade movementFacade;

	@NotNull
	private Double value;

	@NotNull
	private PaymentType paymentType;

	@PostConstruct
	public void init() {
		value = 100.0;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String save() {
		Movement movement = new Movement();
		movement.setDate(Calendar.getInstance());
		movement.setDescription("Aquisição de Crédito.");
		movement.setSource(MovementSource.PURCHASED_CREDIT);
		movement.setType(MovementType.CREDIT);
		movement.setUser(sessionController.getUser());
		movement.setPaymentType(paymentType);
		movement.setValue(value);
		movementFacade.add(movement);

		FacesUtil.addInfoMessage("Crédito adquirido com sucesso.");

		return "my-account?faces-redirect=true";
	}

	public List<PaymentType> getPaymentTypes() {
		return Arrays.asList(PaymentType.values());
	}

}
