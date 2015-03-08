package br.unisinos.pf2.nltest.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;

import br.unisinos.pf2.nltest.executor.ExecutionContext;

public class TestSuite implements Executable {

	private Description description;
	private List<TestCase> testCases;

	@Override
	public void init(String baseScript, String[] args) {
		// TODO tratar args vazio
		this.description = Description.createSuiteDescription(args[0]);
		this.testCases = new ArrayList<>();
	}

	public void addTestCases(List<TestCase> testCases) {
		this.testCases.addAll(testCases);
		for (TestCase testCase : testCases) {
			description.addChild(testCase.getDescription());
		}
	}

	@Override
	public Description getDescription() {
		return description;
	}

	@Override
	public void execute(ExecutionContext ctx) {
		for (TestCase testCase : testCases) {
			testCase.execute(ctx);
		}
	}

	@Override
	public String toString() {
		return description.getDisplayName() + ": " + testCases;
	}

}
