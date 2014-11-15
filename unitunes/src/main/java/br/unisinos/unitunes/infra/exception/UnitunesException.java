package br.unisinos.unitunes.infra.exception;

@SuppressWarnings("serial")
public abstract class UnitunesException extends RuntimeException {

    public UnitunesException(String message) {
        super(message);
    }

	public UnitunesException(Exception e) {
		super(e);
	}
	
}
