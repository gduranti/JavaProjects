package br.unisinos.pf2.nltest.model;

import java.util.ArrayList;
import java.util.List;

import br.unisinos.pf2.nltest.executor.ExecutionContext;

public class TestSuite implements Executable {

	private String description;
	private List<TestCase> testCases;

	@Override
	public void init(String... args) {
		// TODO tratar args vazio
		this.description = args[0];
		this.testCases = new ArrayList<>();
	}

	public void addTestCase(TestCase testCase) {
		testCases.add(testCase);
	}

	@Override
	public void execute(ExecutionContext ctx) {
		for (TestCase testCase : testCases) {
			System.out.println("Executing test suite " + description);
			testCase.execute(ctx);
		}
	}

}
