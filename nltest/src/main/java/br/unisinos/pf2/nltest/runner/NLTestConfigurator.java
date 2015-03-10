package br.unisinos.pf2.nltest.runner;

import br.unisinos.pf2.nltest.executor.Browser;

public interface NLTestConfigurator {

	String getScriptsPath();

	String getProjectName();

	Browser getBrowser();

}
