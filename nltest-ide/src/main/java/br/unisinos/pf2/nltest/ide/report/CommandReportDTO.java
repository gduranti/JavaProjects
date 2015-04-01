package br.unisinos.pf2.nltest.ide.report;

import org.junit.runner.Description;

import br.unisinos.pf2.nltest.core.model.Command;
import br.unisinos.pf2.nltest.core.model.TestCase;
import br.unisinos.pf2.nltest.core.model.TestSuite;
import br.unisinos.pf2.nltest.ide.testexecution.ScriptResult;

public class CommandReportDTO {

	private Description description;
	private String testSuite;
	private String testCase;
	private String command;
	private String result;
	private String message;

	public CommandReportDTO(TestSuite testSuite, TestCase testCase, Command command) {
		this.testSuite = testSuite.getDescription().getDisplayName();
		this.testCase = testCase.getDescription().getDisplayName();
		this.command = command.getDisplayCommand();
		this.description = command.getDescription();
	}

	public void withResult(ScriptResult scriptResult) {
		this.result = scriptResult.getDisplayResult();
		this.message = scriptResult.getDisplayMessage();
	}

	public String getTestSuite() {
		return testSuite;
	}

	public String getTestCase() {
		return testCase;
	}

	public String getCommand() {
		return command;
	}

	public Description getDescription() {
		return description;
	}

	public String getResult() {
		if (result == null) {
			return "Não Executado";
		}
		return result;
	}

	public String getMessage() {
		return message;
	}

}
