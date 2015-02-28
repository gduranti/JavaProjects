package br.unisinos.pf2.nltest.exception;

public class ParseException extends NLTestException {

	private static final long serialVersionUID = 7957952293321685871L;

	public ParseException(String message) {
		super(message);
	}

	public ParseException(String message, Exception cause) {
		super(message, cause);
	}

}
