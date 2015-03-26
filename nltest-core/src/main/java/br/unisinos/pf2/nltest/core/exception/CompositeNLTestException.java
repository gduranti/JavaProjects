package br.unisinos.pf2.nltest.core.exception;

import java.util.List;

public class CompositeNLTestException extends NLTestException {

	private static final long serialVersionUID = 1L;

	public CompositeNLTestException(List<NLTestException> exceptions) {
		super(buildMessage(exceptions));
	}

	private static String buildMessage(List<NLTestException> exceptions) {
		StringBuffer msgBuffer = new StringBuffer();
		for (NLTestException e : exceptions) {
			msgBuffer.append(e.getMessage()).append("\n");
		}
		return msgBuffer.toString();
	}

}
