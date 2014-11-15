package br.unisinos.unitunes.model;

public enum MediaType {

	MUSIC   ("Música",   true),
	BOOK    ("Livro",    false),
	MOVIE   ("Filme",    true),
	PODCAST ("Podacast", true);

	private String description;
	private boolean hasDuration;

	private MediaType(String description, boolean hasDuration) {
		this.description = description;
		this.hasDuration = hasDuration;
	}

	public String getDescription() {
		return description;
	}

	public boolean hasDuration() {
		return hasDuration;
	}

}
