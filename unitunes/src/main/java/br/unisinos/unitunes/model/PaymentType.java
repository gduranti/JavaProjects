package br.unisinos.unitunes.model;

public enum PaymentType {

	CREDIT_CARD ("Cartão de Crédito"),
	BANK_BILL   ("Boleto Bancário"),
	TRANSFER    ("Transferência");
	
	private String description;
	
	private PaymentType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
