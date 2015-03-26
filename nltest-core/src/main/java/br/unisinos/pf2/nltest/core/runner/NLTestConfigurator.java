package br.unisinos.pf2.nltest.core.runner;

import br.unisinos.pf2.nltest.core.executor.Browser;

public interface NLTestConfigurator {

	String getScriptsPath();

	String getProjectName();

	Browser getBrowser();

}
