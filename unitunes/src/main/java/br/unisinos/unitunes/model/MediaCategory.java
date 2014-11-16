package br.unisinos.unitunes.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.unisinos.unitunes.infra.impl.GenericModel;

@Entity
@Table(name = "MEDIAS_CATEGORIES")
public class MediaCategory extends GenericModel {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;

	@NotNull
	private MediaType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}

}
