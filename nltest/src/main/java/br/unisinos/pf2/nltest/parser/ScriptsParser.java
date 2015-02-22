package br.unisinos.pf2.nltest.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import br.unisinos.pf2.nltest.model.Parseable;
import br.unisinos.pf2.nltest.model.TestSuite;

public class ScriptsParser {

	private CommandTranslator commandTranslator;

	public ScriptsParser() {
		commandTranslator = new CommandTranslator();
	}

	public List<TestSuite> parse(String path) {

		TestSuitesBuilder builder = new TestSuitesBuilder();

		Collection<File> files = ScriptsLoader.loadFiles(path);
		for (File file : files) {
			System.out.println("Reading file " + file.getName());
			readFile(builder, file);
		}

		return builder.getResult();
	}

	private void readFile(TestSuitesBuilder builder, File file) {
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				if (!line.isEmpty()) {
					Parseable parseable = commandTranslator.interpret(line);
					builder.add(parseable);
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (scanner != null) {
				IOUtils.closeQuietly(scanner);
			}
		}
	}

}
