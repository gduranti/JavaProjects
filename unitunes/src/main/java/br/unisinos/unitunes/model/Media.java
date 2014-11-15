package br.unisinos.unitunes.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.unisinos.unitunes.infra.impl.GenericModel;

@Entity
@Table(name = "MEDIAS")
public class Media extends GenericModel {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@NotNull
	private Double value;

	private Integer duration;

	@Lob
	private byte[] thumb;

	@Lob
	@NotNull
	private byte[] content;

	@NotNull
	private String fileName;

	@NotNull
	private MediaType type;

	@ManyToOne
	@NotNull
	private User author;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public byte[] getThumb() {
		return thumb;
	}

	public void setThumb(byte[] thumb) {
		this.thumb = thumb;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
