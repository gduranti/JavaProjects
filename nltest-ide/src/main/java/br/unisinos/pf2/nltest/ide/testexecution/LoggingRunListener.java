package br.unisinos.pf2.nltest.ide.testexecution;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingRunListener extends RunListener {

	private Logger logger = LoggerFactory.getLogger(LoggingRunListener.class);

	@Override
	public void testAssumptionFailure(Failure failure) {
		logger.debug("testAssumptionFailure " + failure);
		failure.getException().printStackTrace();
	}

	@Override
	public void testFailure(Failure failure) throws Exception {
		logger.debug("testFailure " + failure);
		failure.getException().printStackTrace();
	}

	@Override
	public void testFinished(Description description) throws Exception {
		logger.debug("testFinished " + description);
	}

	@Override
	public void testIgnored(Description description) throws Exception {
		logger.debug("testIgnored " + description);
	}

	@Override
	public void testRunFinished(Result result) throws Exception {
		logger.debug("testRunFinished " + result);
	}

	@Override
	public void testRunStarted(Description description) throws Exception {
		logger.debug("testRunStarted " + description);
	}

	@Override
	public void testStarted(Description description) throws Exception {
		logger.debug("testStarted " + description);
	}

}
