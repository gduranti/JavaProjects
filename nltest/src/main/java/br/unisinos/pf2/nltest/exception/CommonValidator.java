package br.unisinos.pf2.nltest.exception;

import java.util.ArrayList;
import java.util.List;

public class CommonValidator {

	private List<NLTestException> exceptions = new ArrayList<>();

	public static CommonValidator newValidation() {
		return new CommonValidator();
	}

	public void validate() {
		if (!exceptions.isEmpty()) {
			throw new CompositeNLTestException(exceptions);
		}
	}

	public CommonValidator ifNull(Object object, String message) {
		if (object == null) {
			exceptions.add(new NLTestException(message));
		}
		return this;
	}

}
