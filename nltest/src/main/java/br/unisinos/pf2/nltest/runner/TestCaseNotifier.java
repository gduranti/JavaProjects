package br.unisinos.pf2.nltest.runner;

import org.junit.runner.notification.RunNotifier;

import br.unisinos.pf2.nltest.model.Command;

public class TestCaseNotifier {

	private RunNotifier junitNotifier;
	private Command currentCommand;

	public TestCaseNotifier(RunNotifier junitNotifier) {
		this.junitNotifier = junitNotifier;
	}

	public void setCurrentCommand(Command currentCommand) {
		this.currentCommand = currentCommand;
	}

	public void started() {
		// TODO

	}

	public void failed(Throwable t) {
		// TODO

	}

	public void succesful() {
		// TODO

	}

}
