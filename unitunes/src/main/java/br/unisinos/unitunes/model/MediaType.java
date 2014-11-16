package br.unisinos.unitunes.model;

public enum MediaType {

	MUSIC   ("Música",   true, false),
	BOOK    ("Livro",    false, true),
	MOVIE   ("Filme",    true, false),
	PODCAST ("Podacast", true, false);

	private String description;
	private boolean hasDuration;
	private boolean hasPages;

	private MediaType(String description, boolean hasDuration, boolean hasPages) {
		this.description = description;
		this.hasDuration = hasDuration;
		this.hasPages = hasPages;
	}

	public String getDescription() {
		return description;
	}

	public boolean hasDuration() {
		return hasDuration;
	}

	public boolean hasPages() {
		return hasPages;
	}

}
