package br.unisinos.unitunes.model;

public enum MyMediaFilter {
 
	PUBLISHED ("Publicadas"),
	PURCHASED ("Compradas"),
	FAVORITES ("Favoritas");
	
	private String description;
	
	private MyMediaFilter(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
