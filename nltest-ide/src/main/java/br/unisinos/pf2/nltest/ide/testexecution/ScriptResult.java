package br.unisinos.pf2.nltest.ide.testexecution;

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

	public Result getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public enum Result {
		SUCCESS, FAILURE, IGNORED;
	}

	@Override
	public boolean equals(Object obj) {
		return description.equals(((ScriptResult) obj).getDescription());
	}

}
