package br.unisinos.unitunes.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.unisinos.unitunes.infra.impl.GenericModel;

@Entity
@Table(name = "ALBUMS")
public class Album extends GenericModel {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String description;

	@NotNull
	private Calendar publishedDate;

	@ManyToOne
	@NotNull
	private User author;

	@ManyToMany
	private List<Media> medias;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Calendar getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Calendar publishedDate) {
		this.publishedDate = publishedDate;
	}

	public List<Media> getMedias() {
		return medias;
	}

	public void setMedias(List<Media> medias) {
		this.medias = medias;
	}

}
