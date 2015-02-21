package br.unisinos.pf2.nltest.parser;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public final class ScriptsLoader {

	private static final String SCRIPT_FILE_EXTENSION = "nlt";

	public static File[] loadFiles(String path) {

		File directory = new File(path);

		Collection<File> listFiles = FileUtils.listFiles(directory, new String[] { SCRIPT_FILE_EXTENSION }, true);

		return listFiles.toArray(new File[listFiles.size()]);
	}

}
