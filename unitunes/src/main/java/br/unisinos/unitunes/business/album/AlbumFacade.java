package br.unisinos.unitunes.business.album;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.Hibernate;

import br.unisinos.unitunes.infra.exception.BusinessException;
import br.unisinos.unitunes.infra.impl.GenericFacade;
import br.unisinos.unitunes.model.Album;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.MediaType;

@Stateless
public class AlbumFacade extends GenericFacade<Album> {

	@Inject
	private AlbumDAO dao;

	@PostConstruct
	public void init() {
		setDao(dao);
	}

	@Override
	public Album find(Long id) {
		Album album = super.find(id);
		Hibernate.initialize(album.getMedias());
		return album;
	}

	@Override
	public Album add(Album model) {
		model.setPublishedDate(Calendar.getInstance());
		return super.add(model);
	}

	@Override
	protected void validate(Album album) {

		if (album.getMedias() == null || album.getMedias().isEmpty()) {
			throw new BusinessException("O álbum deve ter pelo menos uma música.");
		}

		for (Media media : album.getMedias()) {
			if (media.getCategory().getType() != MediaType.MUSIC) {
				throw new BusinessException("Apenas músicas podem ser adicionadas ao álbum.");
			}
		}
	}

}
