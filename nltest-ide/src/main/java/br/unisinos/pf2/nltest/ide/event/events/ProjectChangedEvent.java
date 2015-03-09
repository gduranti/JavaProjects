package br.unisinos.pf2.nltest.ide.event.events;

import java.io.File;

public class ProjectChangedEvent implements Event {

	private File oldProject;
	private File newProject;

	public ProjectChangedEvent(File oldProject, File newProject) {
		this.oldProject = oldProject;
		this.newProject = newProject;
	}

	public File getOldProject() {
		return oldProject;
	}

	public File getNewProject() {
		return newProject;
	}

	@Override
	public String toString() {
		return "ProjectChangedEvent[" + oldProject + ", " + newProject + "]";
	}

}
