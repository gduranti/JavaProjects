package br.unisinos.pf2.nltest.core.executor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

import br.unisinos.pf2.nltest.core.exception.NLTestException;

public class FailureHandler {

	private Map<String, String> knownExceptions;

	public Failure handleException(Description description, Throwable e) {
		return new Failure(description, translateException(e));
	}

	private Throwable translateException(Throwable e) {
		String translated = transalteMessage(e.getMessage());
		if (translated != null) {
			return new NLTestException(translated, e);
		} else {
			return e;
		}
	}

	private String transalteMessage(String message) {
		for (Entry<String, String> entry : getKnownExceptions().entrySet()) {
			if (message.contains(entry.getKey())) {
				return entry.getValue();
			}
		}
		return null;
	}

	private Map<String, String> getKnownExceptions() {
		if (knownExceptions == null) {
			knownExceptions = new HashMap<>();
			knownExceptions.put("Cannot navigate to invalid URL", "N�o foi poss�vel abrir a p�gina.");
			knownExceptions.put("no such element", "N�o foi poss�vel encontrar o elemento na p�gina.");
		}
		return knownExceptions;
	}

}
