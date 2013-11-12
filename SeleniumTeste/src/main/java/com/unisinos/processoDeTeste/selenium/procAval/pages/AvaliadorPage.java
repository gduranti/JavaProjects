package com.unisinos.processoDeTeste.selenium.procAval.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AvaliadorPage {

	@FindBy(linkText = "Novo")
	private WebElement botaoNovo;

	@FindBy(id = "commonMessage")
	private WebElement messagemGeral;

	@FindBy(linkText = "Excluir")
	private List<WebElement> botoesExcluir;

	// Campos de insercao
	@FindBy(id = "Nome")
	private WebElement nome;

	@FindBy(id = "Cpf")
	private WebElement cpf;

	@FindBy(xpath = "//span[@for='Nome']")
	private WebElement msgNomeObrigatorio;

	@FindBy(xpath = "//span[@for='Cpf']")
	private WebElement msgCpfObrigatorio;

	@FindBy(css = "button[type=\"button\"]")
	private WebElement botaoSalvar;

	public WebElement getMessagemGeral() {
		return messagemGeral;
	}

	public List<WebElement> getBotoesExcluir() {
		return botoesExcluir;
	}

	public WebElement getNome() {
		return nome;
	}

	public WebElement getCpf() {
		return cpf;
	}

	public WebElement getBotaoNovo() {
		return botaoNovo;
	}

	public WebElement getBotaoSalvar() {
		return botaoSalvar;
	}

	public WebElement getMsgNomeObrigatorio() {
		return msgNomeObrigatorio;
	}

	public WebElement getMsgCpfObrigatorio() {
		return msgCpfObrigatorio;
	}

}
