package br.unisinos.pf2.nltest.parser;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public final class ScriptsLoader {

	private static final String[] SCRIPT_FILE_EXTENSIONS = { "nlt" };

	public static Collection<File> loadFiles(String path) {

		File directory = new File(path);

		Collection<File> listFiles = FileUtils.listFiles(directory, SCRIPT_FILE_EXTENSIONS, true);

		return listFiles;
	}

}
