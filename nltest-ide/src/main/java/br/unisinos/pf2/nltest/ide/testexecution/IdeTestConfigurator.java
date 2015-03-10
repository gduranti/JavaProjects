package br.unisinos.pf2.nltest.ide.testexecution;

import org.junit.runner.RunWith;

import br.unisinos.pf2.nltest.executor.Browser;
import br.unisinos.pf2.nltest.ide.controller.IdePrefs;
import br.unisinos.pf2.nltest.ide.controller.IdeSession;
import br.unisinos.pf2.nltest.runner.NLTestConfigurator;
import br.unisinos.pf2.nltest.runner.NLTestScriptsRunner;

@RunWith(NLTestScriptsRunner.class)
public class IdeTestConfigurator implements NLTestConfigurator {

	@Override
	public String getScriptsPath() {
		return IdeSession.getInstance().getProjectDirectory().getPath();
	}

	@Override
	public String getProjectName() {
		return IdeSession.getInstance().getProjectDirectory().getName();
	}

	@Override
	public Browser getBrowser() {
		return IdePrefs.getDefaultBroser();
	}

}
