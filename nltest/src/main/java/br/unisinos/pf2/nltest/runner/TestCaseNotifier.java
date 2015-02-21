package br.unisinos.pf2.nltest.runner;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

public class TestCaseNotifier {

	private RunNotifier junitNotifier;
	private Description currentDescription;

	public TestCaseNotifier(RunNotifier junitNotifier) {
		this.junitNotifier = junitNotifier;
	}

	public void setCurrentDescription(Description currentDescription) {
		this.currentDescription = currentDescription;
	}

	public void started() {
		junitNotifier.fireTestStarted(currentDescription);
	}

	public void succesful() {
		junitNotifier.fireTestFinished(currentDescription);
	}

	public void failed(Throwable thrownException) {
		junitNotifier.fireTestFailure(new Failure(currentDescription, thrownException));
	}

}
