package br.unisinos.pf2.nltest.executor;

public class Config {

	private String projectName;
	private String scriptsPath;
	private Browser browser;

	public Config() {
		browser = Browser.CHROME;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getScriptsPath() {
		return scriptsPath;
	}

	public void setScriptsPath(String scriptsPath) {
		this.scriptsPath = scriptsPath;
	}

	public Browser getBrowser() {
		return browser;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	public enum Browser {
		CHROME, FIREFOX, IE;
	}

}
