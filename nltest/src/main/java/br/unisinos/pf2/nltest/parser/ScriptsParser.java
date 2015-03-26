package br.unisinos.pf2.nltest.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import br.unisinos.pf2.nltest.exception.ParseException;
import br.unisinos.pf2.nltest.model.Parseable;
import br.unisinos.pf2.nltest.model.TestSuite;

public class ScriptsParser {

	private static final String[] SCRIPT_FILE_EXTENSIONS = { "nlt" };

	private CommandTranslator commandTranslator;

	public ScriptsParser() {
		commandTranslator = new CommandTranslator();
	}

	public List<TestSuite> parse(String path) {

		TestSuitesBuilder builder = new TestSuitesBuilder();

		Collection<File> files = loadFiles(path);
		for (File file : files) {
			System.out.println("Reading file " + file.getName());
			readFile(builder, file);
		}

		return builder.getResult();
	}

	private static Collection<File> loadFiles(String path) {
		File directory = new File(path);
		return FileUtils.listFiles(directory, SCRIPT_FILE_EXTENSIONS, true);
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
			throw new ParseException("Não foi possível encontrar o arquivo de scripts: " + file.getAbsolutePath(), e);
		} finally {
			if (scanner != null) {
				IOUtils.closeQuietly(scanner);
			}
		}
	}

}
