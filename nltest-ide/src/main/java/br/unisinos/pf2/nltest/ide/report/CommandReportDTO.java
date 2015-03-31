package br.unisinos.pf2.nltest.ide.report;

import br.unisinos.pf2.nltest.core.model.Command;
import br.unisinos.pf2.nltest.core.model.TestCase;
import br.unisinos.pf2.nltest.core.model.TestSuite;
import br.unisinos.pf2.nltest.ide.testexecution.ScriptResult.Result;

public class CommandReportDTO {

	private String testSuite;
	private String testCase;
	private String command;
	private Result result;
	private String message;

	public CommandReportDTO(TestSuite testSuite, TestCase testCase, Command command) {
		this.testSuite = testSuite.getDescription().getDisplayName();
		this.testCase = testCase.getDescription().getDisplayName();
		this.command = command.getDisplayCommand();
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

	public String getResult() {
		if (result != null) {
			return result.getName();
		}
		return null;
	}

	public String getMessage() {
		return message;
	}

}
