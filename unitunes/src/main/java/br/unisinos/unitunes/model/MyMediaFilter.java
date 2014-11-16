package br.unisinos.unitunes.model;

public enum MyMediaFilter {
 
	ALL       ("Todas"),
	FAVORITES ("Favoritas");
	
	private String description;
	
	private MyMediaFilter(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
