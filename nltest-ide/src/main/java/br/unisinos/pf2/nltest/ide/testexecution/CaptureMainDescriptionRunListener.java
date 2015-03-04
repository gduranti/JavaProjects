package br.unisinos.pf2.nltest.ide.testexecution;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import br.unisinos.pf2.nltest.ide.controller.DescriptionWrapper;

public class CaptureMainDescriptionRunListener extends RunListener {

	private DescriptionWrapper descriptionWrapper;

	public CaptureMainDescriptionRunListener(DescriptionWrapper executionObserver) {
		this.descriptionWrapper = executionObserver;
	}

	@Override
	public void testRunStarted(Description description) throws Exception {
		if ("null".equals(description.getDisplayName()) && description.getChildren().size() == 1) {
			descriptionWrapper.setRootDescription(description.getChildren().get(0));
		} else {
			descriptionWrapper.setRootDescription(description);
		}
	}

	@Override
	public void testFinished(Description description) throws Exception {
		descriptionWrapper.getResult(description).success();
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		descriptionWrapper.getResult(failure.getDescription()).failure(failure.getException());
	}

	@Override
	public void testIgnored(Description description) throws Exception {
		descriptionWrapper.getResult(description).ignored();
	}

}
