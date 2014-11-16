package br.unisinos.unitunes.model;

public enum MovementSource {

	COMMISION        ("Comiss�o de Venda de M�dia (10%)"),
	SOLD_MEDIA       ("Venda de M�dia (90%)"),
	PURCHASED_MEDIA  ("Compra de M�dia"),
	PURCHASED_CREDIT ("Aquisi��o de Cr�ditos");
	
	private String description;
	
	private MovementSource(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
}
