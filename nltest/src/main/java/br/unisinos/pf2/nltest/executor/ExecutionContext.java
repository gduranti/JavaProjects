package br.unisinos.pf2.nltest.executor;

import org.openqa.selenium.WebDriver;

import br.unisinos.pf2.nltest.runner.TestCaseNotifier;

public class ExecutionContext {

	private TestCaseNotifier notifier;
	private WebDriver driver;

	public ExecutionContext(TestCaseNotifier notifier, WebDriver driver) {
		this.notifier = notifier;
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public TestCaseNotifier getNotifier() {
		return notifier;
	}

}
