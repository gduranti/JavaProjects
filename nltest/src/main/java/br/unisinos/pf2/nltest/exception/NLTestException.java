package br.unisinos.pf2.nltest.exception;

public class NLTestException extends RuntimeException {

	private static final long serialVersionUID = -8384577095136107270L;

	public NLTestException(String message) {
		super(message);
	}

	public NLTestException(String message, Throwable cause) {
		super(message, cause);
	}

}
