package com.unisinos.processoDeTeste.selenium.procAval;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.unisinos.processoDeTeste.selenium.procAval.pages.IndexPage;
import com.unisinos.processoDeTeste.selenium.procAval.pages.LoginPage;

public class ProcAvalLogin {

	private WebDriver driver;

	public ProcAvalLogin(WebDriver driver) {
		this.driver = driver;
	}

	public void login() throws Exception {

		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.getBtnUsuario().click();
		indexPage.getBtnLogin().click();

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.getEmail().sendKeys("gabrielduranti@gmail.com");
		loginPage.getSenha().sendKeys("adm");
		loginPage.getBtnLogin().click();
	}

}
