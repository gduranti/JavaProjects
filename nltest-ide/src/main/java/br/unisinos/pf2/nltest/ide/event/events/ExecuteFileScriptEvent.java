package br.unisinos.pf2.nltest.ide.event.events;

import java.io.File;

public class ExecuteFileScriptEvent implements Event {

	private File file;

	public ExecuteFileScriptEvent(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	@Override
	public String toString() {
		return "ExecuteFileScriptEvent[" + file + "]";
	}

}
