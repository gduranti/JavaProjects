package br.unisinos.pf2.nltest.executor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import br.unisinos.pf2.nltest.exception.NLTestException;

public enum Browser {

	CHROME,

	FIREFOX,

	IE;

	public WebDriver getDriver() {
		switch (this) {
			case IE:
				return new InternetExplorerDriver();
			case FIREFOX:
				return new FirefoxDriver();
			case CHROME:
				return new ChromeDriver();
			default:
				throw new NLTestException("Browser not supported.");
		}
	}

}
