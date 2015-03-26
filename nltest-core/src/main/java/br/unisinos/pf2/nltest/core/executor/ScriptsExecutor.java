package br.unisinos.pf2.nltest.core.executor;

import java.util.List;

import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.WebDriver;

import br.unisinos.pf2.nltest.core.model.TestSuite;

public class ScriptsExecutor {

	private ExecutionContext ctx;

	public ScriptsExecutor(RunNotifier junitNotifier, Browser browser) {
		ctx = createContext(junitNotifier, browser);
	}

	private ExecutionContext createContext(RunNotifier notifier, Browser browser) {
		WebDriver driver = browser.getDriver();
		return new ExecutionContext(notifier, driver);
	}

	public void execute(List<TestSuite> testSuites) {
		try {
			System.out.println("Starting execution.");
			for (TestSuite testSuite : testSuites) {
				testSuite.execute(ctx);
			}
			System.out.println("Finishing execution.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ctx.getDriver().quit();
		}
	}

}
