package br.unisinos.pf2.nltest.ide.exceptions;

import br.unisinos.pf2.nltest.core.exception.NLTestException;

public class NLTestIdeException extends NLTestException {

	private static final long serialVersionUID = 1L;

	public NLTestIdeException(String message) {
		super(message);
	}

	public NLTestIdeException(String message, Throwable cause) {
		super(message, cause);
	}

}
