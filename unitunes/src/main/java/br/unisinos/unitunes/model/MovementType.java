package br.unisinos.unitunes.model;

public enum MovementType {

	CREDIT ("Cr�dito"),
	DEBIT  ("D�bito");
	
	private String description;
	
	private MovementType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
