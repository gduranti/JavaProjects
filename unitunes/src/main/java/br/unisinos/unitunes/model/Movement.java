package br.unisinos.unitunes.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.unisinos.unitunes.infra.impl.GenericModel;

@Entity
@Table(name = "MOVEMENTS")
public class Movement extends GenericModel {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@NotNull
	private User user;

	@NotNull
	private String description;

	@NotNull
	private Double value;

	@NotNull
	private Calendar date;

	@NotNull
	private MovementType type;

	@NotNull
	private MovementSource source;

	private PaymentType paymentType;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MovementType getType() {
		return type;
	}

	public void setType(MovementType type) {
		this.type = type;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public MovementSource getSource() {
		return source;
	}

	public void setSource(MovementSource source) {
		this.source = source;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

}
