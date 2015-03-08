package br.unisinos.pf2.nltest.ide.testexecution;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class ExecutionRunListener extends RunListener {

	private IdeExecutionContext ideExecutionContext;

	public ExecutionRunListener(IdeExecutionContext ideExecutionContext) {
		this.ideExecutionContext = ideExecutionContext;
	}

	@Override
	public void testRunStarted(Description description) throws Exception {
		if ("null".equals(description.getDisplayName()) && description.getChildren().size() == 1) {
			ideExecutionContext.setRootDescription(description.getChildren().get(0));
		} else {
			ideExecutionContext.setRootDescription(description);
		}
	}

	@Override
	public void testFinished(Description description) throws Exception {
		ideExecutionContext.getResult(description).success();
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		ideExecutionContext.getResult(failure.getDescription()).failure(failure.getException());
	}

	@Override
	public void testIgnored(Description description) throws Exception {
		ideExecutionContext.getResult(description).ignored();
	}

}
