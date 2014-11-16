package br.unisinos.unitunes.model;

public enum UserType {
	
	ADMIN    ("Administrador"),
	AUTHOR   ("Autor"),
	ACADEMIC ("Acad�mico");
	
	private String description;
	
	private UserType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
