package br.unisinos.pf2.nltest.runner;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import br.unisinos.pf2.nltest.executor.ScriptsExecutor;
import br.unisinos.pf2.nltest.model.TestSuite;
import br.unisinos.pf2.nltest.parser.ScriptsParser;

public class NLTestScriptsRunner extends Runner {

	private List<TestSuite> testSuites;
	private Description description;

	public NLTestScriptsRunner(Class<? extends NLTestConfigurator> testClass) throws Exception {

		NLTestConfigurator testClassInstance = testClass.newInstance();

		ScriptsParser parser = new ScriptsParser();
		testSuites = parser.parse(testClassInstance.getScriptsPath());

		// DescriptionBuilder descriptionBuilder = new DescriptionBuilder();
		// description = descriptionBuilder.build(testSuites);

		description = Description.createSuiteDescription("Root Description");
		for (TestSuite testSuite : testSuites) {
			description.addChild(testSuite.getDescription());
		}
	}

	@Override
	public Description getDescription() {
		return description;
	}

	@Override
	public void run(RunNotifier junitNotifier) {

		ScriptsExecutor executor = new ScriptsExecutor(junitNotifier);
		executor.execute(testSuites);

	}

}
