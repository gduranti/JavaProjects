package br.unisinos.unitunes.controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.unisinos.unitunes.business.user.UserFacade;
import br.unisinos.unitunes.infra.impl.ListController;
import br.unisinos.unitunes.model.User;

@Named
@ViewAccessScoped
public class UserListController extends ListController<User> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserFacade userFacade;

	@PostConstruct
	public void initFacade() {
		setFacade(userFacade);
	}
	
	@Override
	protected User createModelInstance() {
		return new User();
	}

	@Override
	public String getViewTitle() {
		return "Lista de Usuários";
	}

}
