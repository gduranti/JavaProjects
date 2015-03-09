package br.unisinos.pf2.nltest.ide.testexecution;

import java.io.File;

import org.junit.runner.JUnitCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unisinos.pf2.nltest.ide.controller.thread.ExecuteTestsThread;

public class JUnitExecutor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteTestsThread.class);

	public void execute(IdeExecutionContext ideExecutionContext, File file) {
		LOGGER.debug("Starting test execution...");

		// TODO
		System.setProperty("webdriver.ie.driver", "E:/Java/GitHub/Unisinos/nltest-ide/src/main/resources/webdrivers/IEDriverServer.exe");
		System.setProperty("webdriver.chrome.driver", "E:/Java/GitHub/Unisinos/nltest-ide/src/main/resources/webdrivers/chromedriver.exe");

		IdeTestConfigurator.scriptsPath = file.getAbsolutePath();
		IdeTestConfigurator.projectName = file.getName();

		JUnitCore jUnitCore = new JUnitCore();
		jUnitCore.addListener(new LoggingRunListener());
		jUnitCore.addListener(new ExecutionRunListener(ideExecutionContext));
		jUnitCore.run(IdeTestConfigurator.class);

		LOGGER.debug("Test execution finished.");
	}

}
