package br.unisinos.pf2.nltest;

import org.junit.runner.RunWith;

import br.unisinos.pf2.nltest.executor.Browser;
import br.unisinos.pf2.nltest.runner.NLTestConfigurator;
import br.unisinos.pf2.nltest.runner.NLTestScriptsRunner;

/**
 *
 * Classe de testes...
 *
 */
@RunWith(NLTestScriptsRunner.class)
public class TestClass implements NLTestConfigurator {

	public TestClass() {
		System.setProperty("webdriver.ie.driver", "E:/Java/GitHub/Unisinos/nltest-ide/src/main/resources/webdrivers/IEDriverServer.exe");
		System.setProperty("webdriver.chrome.driver", "E:/Java/GitHub/Unisinos/nltest-ide/src/main/resources/webdrivers/chromedriver.exe");
	}

	@Override
	public String getScriptsPath() {
		return "E:\\Java\\GitHub\\Unisinos\\nltest\\src\\test\\resources\\scripts-folder";
	}

	@Override
	public String getProjectName() {
		return "Projeto enquanto desenvolvimento";
	}

	@Override
	public Browser getBrowser() {
		return Browser.CHROME;
	}

}
