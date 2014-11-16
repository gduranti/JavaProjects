package br.unisinos.unitunes.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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

	private Double value;

	private Integer duration;

	private Integer pageCount;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inclusionDate;

	@Lob
	private byte[] thumb;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private MediaContent content;

	@NotNull
	private String fileName;

	@NotNull
	@ManyToOne
	private MediaCategory category;

	@NotNull
	@ManyToOne
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

	public MediaContent getContent() {
		return content;
	}

	public void setContent(MediaContent content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public MediaCategory getCategory() {
		return category;
	}

	public void setCategory(MediaCategory category) {
		this.category = category;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Calendar getInclusionDate() {
		return inclusionDate;
	}

	public void setInclusionDate(Calendar inclusionDate) {
		this.inclusionDate = inclusionDate;
	}

}
