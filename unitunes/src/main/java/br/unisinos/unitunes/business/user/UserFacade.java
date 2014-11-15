package br.unisinos.unitunes.business.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.unisinos.unitunes.infra.exception.BusinessException;
import br.unisinos.unitunes.infra.impl.GenericFacade;
import br.unisinos.unitunes.model.User;
import br.unisinos.unitunes.model.UserType;

@Stateless
public class UserFacade extends GenericFacade<User> {

	@Inject
	private UserDAO userDAO;

	@PostConstruct
	public void init() {
		super.setDao(userDAO);
	}

	@Override
	public User add(User model) {
		model.setType(UserType.ACADEMIC);
		return super.add(model);
	}

	public User loggin(String email, String password) {
		User example = new User();
		example.setEmail(email);
		example.setPassword(password);
		List<User> list = userDAO.list(example);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		throw new BusinessException("Usuário ou senha incorreta.");
	}

}
