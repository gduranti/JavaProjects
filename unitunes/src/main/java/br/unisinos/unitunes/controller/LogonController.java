package br.unisinos.unitunes.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.unisinos.unitunes.business.user.UserFacade;
import br.unisinos.unitunes.infra.session.SessionController;
import br.unisinos.unitunes.model.User;

@Named
@RequestScoped
public class LogonController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionController sessionController;

	@Inject
	private UserFacade userFacade;

	@NotNull
	private String email;

	@NotNull
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String loggin() {
		User user = userFacade.loggin(email, password);
		sessionController.loggin(user);
		return "user-home?faces-redirect=true";
	}

}
