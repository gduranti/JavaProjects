package com.unisinos.processoDeTeste.selenium.procAval.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(id = "Email")
	private WebElement email;

	@FindBy(id = "Senha")
	private WebElement senha;

	@FindBy(css = "button.btn")
	private WebElement btnLogin;

	public WebElement getEmail() {
		return email;
	}

	public WebElement getSenha() {
		return senha;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

}
