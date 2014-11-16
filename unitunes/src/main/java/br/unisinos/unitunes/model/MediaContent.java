package br.unisinos.unitunes.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.unisinos.unitunes.infra.impl.GenericModel;

@Entity
@Table(name = "MEDIAS_CONTENTS")
public class MediaContent extends GenericModel {

	private static final long serialVersionUID = 1L;

	@Lob
	@NotNull
	private byte[] content;

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
