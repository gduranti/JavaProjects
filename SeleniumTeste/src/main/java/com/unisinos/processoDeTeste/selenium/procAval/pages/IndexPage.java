package com.unisinos.processoDeTeste.selenium.procAval.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage {

	@FindBy(linkText = "Usuário")
	private WebElement btnUsuario;

	@FindBy(linkText = "Login")
	private WebElement btnLogin;

	@FindBy(linkText = "Avaliador")
	private WebElement menuAvaliador;

	public WebElement getBtnUsuario() {
		return btnUsuario;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

	public WebElement getMenuAvaliador() {
		return menuAvaliador;
	}

}
