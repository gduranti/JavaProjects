package br.unisinos.pf2.nltest.parser;

import java.io.File;

import br.unisinos.pf2.nltest.model.TestSuite;

public class ScriptsParser {

	private CommandTranslator commandTranslator;

	public TestSuite[] parse(String path) {

		// TODO

		File[] files = ScriptsLoader.loadFiles(path);

		TestSuite[] testSuites = new TestSuite[files.length];

		for (File file : files) {

		}

		return testSuites;
	}

}
