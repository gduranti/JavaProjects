package br.unisinos.pf2.nltest.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.unisinos.pf2.nltest.model.Command;
import br.unisinos.pf2.nltest.model.Executable;
import br.unisinos.pf2.nltest.model.TestCase;
import br.unisinos.pf2.nltest.model.TestSuite;

public class ScriptsParser {

	private CommandTranslator commandTranslator;

	public ScriptsParser() {
		commandTranslator = new CommandTranslator();
	}

	public List<TestSuite> parse(String path) {

		// TODO

		File[] files = ScriptsLoader.loadFiles(path);

		List<TestSuite> testSuites = new ArrayList<>();

		for (File file : files) {

			System.out.println("Reading file " + file.getName());

			TestSuite testSuite = null;
			TestCase testCase = null;

			Scanner scanner = null;
			try {
				scanner = new Scanner(file);

				while (scanner.hasNext()) {

					String line = scanner.nextLine();

					Executable executable = commandTranslator.translate(line);

					if (executable instanceof TestSuite) {
						testSuite = (TestSuite) executable;
						testSuites.add(testSuite);
					} else if (executable instanceof TestCase) {
						testCase = (TestCase) executable;
						testSuite.addTestCase(testCase);
					} else if (executable instanceof Command) {
						testCase.addCommand((Command) executable);
					} else {
						// TODO
					}

					// System.out.println(line);

				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (scanner != null) {
					scanner.close();
				}
			}
		}

		return testSuites;
	}

	public static void main(String[] args) {
		new ScriptsParser().parse("E:\\Java\\GitHub\\Unisinos\\nltest\\src\\test\\resources\\scripts-folder");
	}

}
