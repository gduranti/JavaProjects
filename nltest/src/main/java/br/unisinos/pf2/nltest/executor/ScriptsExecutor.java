package br.unisinos.pf2.nltest.executor;

import java.util.List;

import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import br.unisinos.pf2.nltest.exception.NLTestException;
import br.unisinos.pf2.nltest.model.TestSuite;
import br.unisinos.pf2.nltest.runner.TestCaseNotifier;

public class ScriptsExecutor {

	private ExecutionContext ctx;

	public ScriptsExecutor(RunNotifier junitNotifier, Config config) {
		ctx = createContext(junitNotifier, config);
	}

	private ExecutionContext createContext(RunNotifier junitNotifier, Config config) {
		TestCaseNotifier notifier = new TestCaseNotifier(junitNotifier);
		WebDriver driver = createDriver(config);
		return new ExecutionContext(notifier, driver);
	}

	private WebDriver createDriver(Config config) {
		switch (config.getBrowser()) {
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
