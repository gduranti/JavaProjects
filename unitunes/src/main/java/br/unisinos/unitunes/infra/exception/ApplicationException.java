package br.unisinos.unitunes.infra.exception;

public class ApplicationException extends UnitunesException {
	
	private static final long serialVersionUID = 1L;

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Exception e) {
		super(e);
	}
	
}
