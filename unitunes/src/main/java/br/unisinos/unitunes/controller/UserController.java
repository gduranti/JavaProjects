package br.unisinos.unitunes.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.unisinos.unitunes.business.movement.MovementFacade;
import br.unisinos.unitunes.business.user.UserFacade;
import br.unisinos.unitunes.infra.exception.BusinessException;
import br.unisinos.unitunes.infra.faces.FacesUtil;
import br.unisinos.unitunes.infra.impl.FormController;
import br.unisinos.unitunes.infra.session.SessionController;
import br.unisinos.unitunes.model.Movement;
import br.unisinos.unitunes.model.MovementType;
import br.unisinos.unitunes.model.User;

@Named
@ViewAccessScoped
public class UserController extends FormController<User> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionController sessionController;

	@Inject
	private UserFacade facade;

	@Inject
	private MovementFacade movementFacade;

	private List<Movement> movements;
	private Double balance;

	@PostConstruct
	public void setFacade() {
		super.setFacade(facade);
		if (sessionController.getUser() != null) {
			setId(sessionController.getUser().getId());
		}
	}

	@NotNull
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public Double getBalance() {
		return balance;
	}

	@Override
	protected User load() {
		User user = super.load();

		Movement example = new Movement();
		example.setUser(user);
		movements = movementFacade.list(example);

		balance = 0.0;
		for (Movement movement : movements) {
			if (movement.getType() == MovementType.CREDIT) {
				balance += movement.getValue();
			} else {
				balance -= movement.getValue();
			}
		}

		return user;
	}

	@Override
	public void save() {
		if (!confirmPassword.equals(getModel().getPassword())) {
			throw new BusinessException("As senhas digitadas não conferem.");
		}

		super.save();

		if (isInclusion()) {
			sessionController.login(getModel());
		} else {
			FacesUtil.addInfoMessage("Dados cadastrais alterados com sucesso.");
		}
	}

	@Override
	public String close() {
		return "user-home?faces-redirect=true";
	}

}
