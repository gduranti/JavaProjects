package br.unisinos.pf2.nltest.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;

import br.unisinos.pf2.nltest.executor.ExecutionContext;

public class TestSuite implements Executable {

	private Description description;
	private List<TestCase> testCases;

	@Override
	public void init(String... args) {
		// TODO tratar args vazio
		this.description = Description.createSuiteDescription(args[0]);
		this.testCases = new ArrayList<>();
	}

	public void addTestCase(TestCase testCase) {
		testCases.add(testCase);
		description.addChild(testCase.getDescription());
	}

	@Override
	public Description getDescription() {
		return description;
	}

	public List<TestCase> peekTestCases() {
		return new ArrayList<TestCase>(testCases);
	}

	@Override
	public void execute(ExecutionContext ctx) {
		for (TestCase testCase : testCases) {
			System.out.println("Executing test suite " + description.getDisplayName());
			testCase.execute(ctx);
		}
	}

}
