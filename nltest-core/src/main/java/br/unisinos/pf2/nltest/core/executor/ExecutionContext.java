package br.unisinos.pf2.nltest.core.executor;

import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.WebDriver;

public class ExecutionContext {

	private RunNotifier notifier;
	private WebDriver driver;

	public ExecutionContext(RunNotifier notifier, WebDriver driver) {
		this.notifier = notifier;
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public RunNotifier getNotifier() {
		return notifier;
	}

}
