package com.unisinos.processoDeTeste.seleniumTeste.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage {

	@FindBy(name = "q")
	private WebElement searchBox;

	@FindBy(name = "btnG")
	private WebElement searchButton;
	
	@FindBy(name = "res")
	private WebElement searchResult;

	public void setSearchBoxKeys(String keysToSend) {
		searchBox.sendKeys(keysToSend);
	}
	
	public void clickSearchButton() {
		searchButton.click();
	}
	
	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getSearchResult() {
		return searchResult;
	}
	
}
