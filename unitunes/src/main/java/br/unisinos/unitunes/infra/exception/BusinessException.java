package br.unisinos.unitunes.infra.exception;

@SuppressWarnings("serial")
public class BusinessException extends UnitunesException {

	public BusinessException(String message) {
		super(message);
	}
	
}
