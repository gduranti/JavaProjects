package br.unisinos.pf2.nltest.ide.filemanagement;

public class ScriptFile {

	private String scriptContent;
	private boolean directory;
	private String name;

	public String getScriptContent() {
		return scriptContent;
	}

	public void setScriptContent(String scriptContent) {
		this.scriptContent = scriptContent;
	}

	public boolean isDirectory() {
		return directory;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	public static ScriptFile createDirectory(String name) {
		ScriptFile scriptFile = new ScriptFile();
		scriptFile.directory = true;
		scriptFile.name = name;
		return scriptFile;
	}

	public static ScriptFile createFile(String name) {
		return createFile(name, "");
	}

	public static ScriptFile createFile(String name, String scriptContent) {
		ScriptFile scriptFile = new ScriptFile();
		scriptFile.scriptContent = scriptContent;
		scriptFile.directory = false;
		scriptFile.name = name;
		return scriptFile;
	}
}
