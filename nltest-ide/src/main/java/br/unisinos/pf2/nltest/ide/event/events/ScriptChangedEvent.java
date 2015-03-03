package br.unisinos.pf2.nltest.ide.event.events;

import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFile;

public class ScriptChangedEvent implements Event {

	private ScriptFile oldScript;
	private ScriptFile newScript;

	public ScriptChangedEvent(ScriptFile oldScript, ScriptFile newScript) {
		this.oldScript = oldScript;
		this.newScript = newScript;
	}

	public ScriptFile getOldScript() {
		return oldScript;
	}

	public ScriptFile getNewScript() {
		return newScript;
	}

	@Override
	public String toString() {
		return "ScriptChanged[" + oldScript + ", " + newScript + "]";
	}

}
