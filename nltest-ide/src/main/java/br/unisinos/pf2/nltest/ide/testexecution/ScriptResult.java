package br.unisinos.pf2.nltest.ide.testexecution;

import org.apache.commons.lang3.StringUtils;
import org.junit.runner.Description;

public class ScriptResult {

	private Description description;
	private Result result;
	private String message;

	public ScriptResult(Description description) {
		this.description = description;
	}

	public void success() {
		this.result = Result.SUCCESS;
	}

	public void ignored() {
		this.result = Result.IGNORED;
	}

	public void failure(Throwable t) {
		this.result = Result.FAILURE;
		message = t.getMessage();
	}

	public Description getDescription() {
		return description;
	}

	public String getDisplayName() {
		return StringUtils.removePattern(description.getDisplayName(), "\\(.*\\)");
	}

	public boolean wasExecuted() {
		return result != null;
	}

	public String getDisplayResult() {
		return wasExecuted() ? result.name : null;
	}

	public String getDisplayMessage() {
		if (message == null) {
			return null;
		}
		return message.replace("expected:", "Esperado:").replace("but was:", "mas encontrado:");
	}

	@Override
	public boolean equals(Object obj) {
		if (description == null || obj == null) {
			return false;
		}
		return description.equals(((ScriptResult) obj).getDescription());
	}

	public enum Result {
		SUCCESS("Sucesso"), FAILURE("Falha"), IGNORED("Ignorado");

		private String name;

		private Result(String name) {
			this.name = name;
		}
	}

}
