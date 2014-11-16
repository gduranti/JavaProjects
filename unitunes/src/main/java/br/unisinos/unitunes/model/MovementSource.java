package br.unisinos.unitunes.model;

public enum MovementSource {

	COMMISION        ("Comissão de Venda de Mídia (10%)"),
	SOLD_MEDIA       ("Venda de Mídia (90%)"),
	PURCHASED_MEDIA  ("Compra de Mídia"),
	PURCHASED_CREDIT ("Aquisição de Créditos");
	
	private String description;
	
	private MovementSource(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	
}
