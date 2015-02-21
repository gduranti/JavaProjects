package br.unisinos.pf2.nltest.executor;

import java.util.List;

import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.unisinos.pf2.nltest.model.TestSuite;
import br.unisinos.pf2.nltest.runner.TestCaseNotifier;

public class ScriptsExecutor {

	private ExecutionContext ctx;

	public ScriptsExecutor(RunNotifier junitNotifier) {
		ctx = createContext(junitNotifier);
	}

	private ExecutionContext createContext(RunNotifier junitNotifier) {
		TestCaseNotifier notifier = new TestCaseNotifier(junitNotifier);
		WebDriver driver = new FirefoxDriver();
		ExecutionContext ctx = new ExecutionContext(notifier, driver);
		return ctx;
	}

	public void execute(List<TestSuite> testSuites) {
		System.out.println("Starting execution.");
		for (TestSuite testSuite : testSuites) {
			testSuite.execute(ctx);
		}
		System.out.println("Finishing execution.");
		ctx.getDriver().quit();
	}

}
