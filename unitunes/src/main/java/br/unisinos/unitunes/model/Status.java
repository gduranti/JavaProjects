package br.unisinos.unitunes.model;

public enum Status {

	ACTIVE   ("Ativo"),
	INACTIVE ("Inativo");
	
	private String description;

	private Status(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
