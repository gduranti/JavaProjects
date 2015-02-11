package br.unisinos.pf2.nltest.parser;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public final class ScriptsLoader {

	public static File[] loadFiles(String path) {

		File directory = new File(path);

		Collection<File> listFiles = FileUtils.listFiles(directory, new String[] { ".nlt" }, true);

		return listFiles.toArray(new File[listFiles.size()]);
	}
}
