package br.unisinos.unitunes.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.unisinos.unitunes.business.movement.MovementFacade;
import br.unisinos.unitunes.infra.faces.FacesUtil;
import br.unisinos.unitunes.model.PaymentType;

@Named
@ViewAccessScoped
public class CreditPurchaseController implements Serializable {

	private static final long serialVersionUID = 1L;

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
		movementFacade.generateCreditPurchasedMovement(paymentType, value);
		FacesUtil.addInfoMessage("Crédito adquirido com sucesso.");
		return "my-account?faces-redirect=true";
	}

	public List<PaymentType> getPaymentTypes() {
		return Arrays.asList(PaymentType.values());
	}

}
