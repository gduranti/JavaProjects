package br.unisinos.pf2.nltest.core.parser;

import java.util.ArrayList;
import java.util.List;

import br.unisinos.pf2.nltest.core.exception.ParseException;
import br.unisinos.pf2.nltest.core.model.Command;
import br.unisinos.pf2.nltest.core.model.ParameterSetData;
import br.unisinos.pf2.nltest.core.model.ParameterSetMaker;
import br.unisinos.pf2.nltest.core.model.Parseable;
import br.unisinos.pf2.nltest.core.model.TestCase;
import br.unisinos.pf2.nltest.core.model.TestSuite;

public class TestSuitesBuilder {

	private List<TestSuite> testSuites = new ArrayList<>();
	private TestSuite currentTestSuite = null;
	private TestCaseBuilder testCaseBuilder = new TestCaseBuilder();

	public void add(Parseable parseable) {

		if (parseable instanceof TestSuite) {
			buildPreviusTestCase();
			currentTestSuite = (TestSuite) parseable;
			testSuites.add(currentTestSuite);

		} else if (parseable instanceof TestCase) {
			buildPreviusTestCase();
			testCaseBuilder.setTestCase((TestCase) parseable);

		} else if (parseable instanceof Command) {
			testCaseBuilder.addCommand((Command) parseable);

		} else if (parseable instanceof ParameterSetMaker) {
			testCaseBuilder.withParameterSet();

		} else if (parseable instanceof ParameterSetData) {
			testCaseBuilder.addParameterSetData((ParameterSetData) parseable);

		} else {
			throw new ParseException("Linha parseável não identificada: " + parseable.getClass());
		}
	}

	private void buildPreviusTestCase() {
		List<TestCase> testCases = testCaseBuilder.build();
		testCaseBuilder.reset();
		if (!testCases.isEmpty()) {
			currentTestSuite.addTestCases(testCases);
		}
	}

	public List<TestSuite> getResult() {
		buildPreviusTestCase();
		return testSuites;
	}

}
