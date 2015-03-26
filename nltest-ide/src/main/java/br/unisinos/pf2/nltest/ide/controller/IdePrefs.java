package br.unisinos.pf2.nltest.ide.controller;

import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import br.unisinos.pf2.nltest.core.executor.Browser;

public class IdePrefs {

	private static final String PREFS_NODE = "br/unisinos/nltest-ide";
	private static final String LAST_PROJECT_DIR_KEY = "LAST_PROJECT_DIR";
	private static final String DEFAULT_BROWSER_KEY = "DEFAULT_BROWSER";

	public static void saveLastDirectory(File directoryPath) {
		if (directoryPath != null) {
			save(LAST_PROJECT_DIR_KEY, directoryPath.getPath());
		}
	}

	public static File getLastDirectory() {
		String lastDirectory = load(LAST_PROJECT_DIR_KEY);
		if (lastDirectory != null) {
			return new File(lastDirectory);
		}
		return null;
	}

	public static void saveDefaultBrowser(Browser browser) {
		save(DEFAULT_BROWSER_KEY, browser.name());
	}

	public static Browser getDefaultBroser() {
		String name = load(DEFAULT_BROWSER_KEY);
		if (name != null) {
			return Browser.valueOf(name);
		}
		return Browser.CHROME;
	}

	private static String load(String key) {
		return loadPreferences().get(key, null);
	}

	private static void save(String key, String value) {
		try {
			Preferences prefs = loadPreferences();
			prefs.put(key, value);
			prefs.flush();
		} catch (BackingStoreException e) {
		}
	}

	private static Preferences loadPreferences() {
		return Preferences.userRoot().node(PREFS_NODE);
	}

}
