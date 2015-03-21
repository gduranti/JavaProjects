package br.unisinos.pf2.nltest.runner;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import br.unisinos.pf2.nltest.exception.CommonValidator;
import br.unisinos.pf2.nltest.executor.Browser;
import br.unisinos.pf2.nltest.executor.ScriptsExecutor;
import br.unisinos.pf2.nltest.model.TestSuite;
import br.unisinos.pf2.nltest.parser.ScriptsParser;

public class NLTestScriptsRunner extends Runner {

	private List<TestSuite> testSuites;
	private Description description;
	private Browser browser;

	public NLTestScriptsRunner(Class<? extends NLTestConfigurator> testClass) throws Exception {
		NLTestConfigurator testClassInstance = testClass.newInstance();

		validate(testClassInstance);

		ScriptsParser parser = new ScriptsParser();
		testSuites = parser.parse(testClassInstance.getScriptsPath());
		browser = testClassInstance.getBrowser();
		description = Description.createSuiteDescription(testClassInstance.getProjectName());
		for (TestSuite testSuite : testSuites) {
			description.addChild(testSuite.getDescription());
		}
	}

	private void validate(NLTestConfigurator configurator) {
		CommonValidator.newValidation()
			.ifNull(configurator.getBrowser(),     "O browser deve ser informado.")
			.ifNull(configurator.getProjectName(), "O nome do projeto deve ser informado.")
			.ifNull(configurator.getScriptsPath(), "A pasta dos scripts deve ser informada.").validate();
	}

	@Override
	public Description getDescription() {
		return description;
	}

	@Override
	public void run(RunNotifier junitNotifier) {
		ScriptsExecutor executor = new ScriptsExecutor(junitNotifier, browser);
		executor.execute(testSuites);
	}

}
