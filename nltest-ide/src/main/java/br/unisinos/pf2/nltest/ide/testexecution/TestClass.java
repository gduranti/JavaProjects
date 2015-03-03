package br.unisinos.pf2.nltest.ide.testexecution;

import org.junit.runner.RunWith;

import br.unisinos.pf2.nltest.runner.NLTestConfigurator;
import br.unisinos.pf2.nltest.runner.NLTestScriptsRunner;

@RunWith(NLTestScriptsRunner.class)
public class TestClass implements NLTestConfigurator {

	static String scriptsPath;
	static String projectName;

	@Override
	public String getScriptsPath() {
		return scriptsPath;
	}

	@Override
	public String getProjectName() {
		return projectName;
	}

}
