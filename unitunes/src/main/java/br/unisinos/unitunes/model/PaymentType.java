package br.unisinos.unitunes.model;

public enum PaymentType {

	CREDIT_CARD ("Cart�o de Cr�dito"),
	BANK_BILL   ("Boleto Banc�rio"),
	TRANSFER    ("Transfer�ncia");
	
	private String description;
	
	private PaymentType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
