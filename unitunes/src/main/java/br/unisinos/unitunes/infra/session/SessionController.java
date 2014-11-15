package br.unisinos.unitunes.infra.session;

import java.io.Serializable;
import java.util.Calendar;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.unisinos.unitunes.model.User;

@Named
@SessionScoped
public class SessionController implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;
	private Calendar logginDate;

	public User getUser() {
		return user;
	}

	public void loggin(User user) {
		this.user = user;
		this.logginDate = Calendar.getInstance();
	}

	public void loggoff() {
		this.user = null;
		this.logginDate = null;
	}

	public Calendar getLogginDate() {
		return logginDate;
	}

	public boolean isLogged() {
		return user != null;
	}

}
