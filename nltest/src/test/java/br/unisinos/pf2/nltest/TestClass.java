package br.unisinos.pf2.nltest;

import org.junit.runner.RunWith;

import br.unisinos.pf2.nltest.runner.NLTestConfigurator;
import br.unisinos.pf2.nltest.runner.NLTestScriptsRunner;

/**
 *
 * Classe de testes...
 *
 */
@RunWith(NLTestScriptsRunner.class)
public class TestClass implements NLTestConfigurator {

	@Override
	public String getScriptsPath() {
		return "E:\\Java\\GitHub\\Unisinos\\nltest\\src\\test\\resources\\scripts-folder";
	}

	@Override
	public String getProjectName() {
		return "Projeto enquanto desenvolvimento";
	}

}
