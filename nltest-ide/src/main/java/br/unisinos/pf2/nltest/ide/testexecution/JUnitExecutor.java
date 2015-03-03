package br.unisinos.pf2.nltest.ide.testexecution;

import java.io.File;

import org.junit.runner.JUnitCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JUnitExecutor {

	private static final Logger LOGGER = LoggerFactory.getLogger(JUnitExecutor.class);

	public void execute(File file) {

		LOGGER.debug("Starting execution...");

		// TODO
		System.setProperty("webdriver.ie.driver", "E:/Java/GitHub/Unisinos/nltest-ide/src/main/resources/webdrivers/IEDriverServer.exe");
		System.setProperty("webdriver.chrome.driver", "E:/Java/GitHub/Unisinos/nltest-ide/src/main/resources/webdrivers/chromedriver.exe");

		// TODO
		TestClass.scriptsPath = "E:/Java/GitHub/Unisinos/nltest/src/test/resources/scripts-folder-simple";
		TestClass.projectName = "Projeto enquanto desenvolvimento";

		JUnitCore jUnitCore = new JUnitCore();
		jUnitCore.addListener(new LoggingRunListener());
		jUnitCore.run(TestClass.class);

		LOGGER.debug("Starting finished");

	}

}
