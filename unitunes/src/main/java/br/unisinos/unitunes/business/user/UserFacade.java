package br.unisinos.unitunes.business.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.hibernate.Hibernate;

import br.unisinos.unitunes.infra.exception.BusinessException;
import br.unisinos.unitunes.infra.impl.GenericFacade;
import br.unisinos.unitunes.model.Media;
import br.unisinos.unitunes.model.User;
import br.unisinos.unitunes.model.UserType;
import br.unisinos.unitunes.model.event.MediaChangedEvent;
import br.unisinos.unitunes.model.event.UserInfoChangedEvent;

@Stateless
public class UserFacade extends GenericFacade<User> {

	@Inject
	private UserDAO userDAO;

	@Inject
	private Event<UserInfoChangedEvent> userInfoChangedEvent;

	@PostConstruct
	public void init() {
		super.setDao(userDAO);
	}

	@Override
	public User add(User user) {
		validateUniqueEmail(user);
		user.setType(UserType.ACADEMIC);
		user = super.add(user);
		return user;
	}

	@Override
	public User update(User user) {
		user = super.update(user);
		userInfoChangedEvent.fire(new UserInfoChangedEvent(user));
		return user;
	}

	public User addPurchasedMedia(Media media, User user) {
		user.getPurchasedMedias().add(media);
		update(user);
		return user;
	}

	public User addFavoriteMedia(Media media, User user) {
		user.getFavoritesMedias().add(media);
		update(user);
		return user;
	}

	public User removeFavoriteMedia(Media media, User user) {
		user.getFavoritesMedias().remove(media);
		update(user);
		return user;
	}

	public User loggin(String email, String password) {
		User example = new User();
		example.setEmail(email);
		example.setPassword(password);
		List<User> list = userDAO.list(example);
		if (!list.isEmpty()) {
			User user = list.get(0);
			Hibernate.initialize(user.getPublishedMedias());
			Hibernate.initialize(user.getFavoritesMedias());
			Hibernate.initialize(user.getPurchasedMedias());
			Hibernate.initialize(user.getPublishedAlbums());
			return user;
		}
		throw new BusinessException("Usu�rio ou senha incorreta.");
	}

	public void observeMedias(@Observes MediaChangedEvent mediaChangedEvent) {
		User user = mediaChangedEvent.getMedia().getAuthor();
		if (user.getType() == UserType.ACADEMIC) {
			user.setType(UserType.AUTHOR);
			update(user);
		}
	}

	private void validateUniqueEmail(User user) {
		User example = new User();
		example.setEmail(user.getEmail());
		Long count = count(example);
		if (count > 0) {
			throw new BusinessException("Este e-mail j� est� cadastrado.");
		}
	}

}
