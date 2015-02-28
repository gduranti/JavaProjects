package br.unisinos.pf2.nltest.runner;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import br.unisinos.pf2.nltest.exception.CommonValidator;
import br.unisinos.pf2.nltest.executor.Config;
import br.unisinos.pf2.nltest.executor.ScriptsExecutor;
import br.unisinos.pf2.nltest.executor.Config.Browser;
import br.unisinos.pf2.nltest.model.TestSuite;
import br.unisinos.pf2.nltest.parser.ScriptsParser;

public class NLTestScriptsRunner extends Runner {

	private List<TestSuite> testSuites;
	private Description description;
	private Config config;

	public NLTestScriptsRunner(Class<? extends NLTestConfigurator> testClass) throws Exception {
		NLTestConfigurator testClassInstance = testClass.newInstance();
		Config config = new Config();
		config.setProjectName(testClassInstance.getProjectName());
		config.setScriptsPath(testClassInstance.getScriptsPath());
		config.setBrowser(Browser.CHROME);
		configure(config);
	}

	public NLTestScriptsRunner(Config config) throws Exception {
		configure(config);
	}

	private void configure(Config config) {
		
		validate(config);
		this.config = config;
		
		ScriptsParser parser = new ScriptsParser();
		testSuites = parser.parse(config.getScriptsPath());

		description = Description.createSuiteDescription(config.getProjectName());
		for (TestSuite testSuite : testSuites) {
			description.addChild(testSuite.getDescription());
		}
	}

	private void validate(Config config) {
		CommonValidator.newValidation()
			.ifNotNul(config.getBrowser(),     "O browser deve ser informado.")
			.ifNotNul(config.getProjectName(), "O nome do projeto deve ser informado.")
			.ifNotNul(config.getScriptsPath(), "A pasta dos scripts deve ser informada.")
			.validate();
	}

	@Override
	public Description getDescription() {
		return description;
	}

	@Override
	public void run(RunNotifier junitNotifier) {
		ScriptsExecutor executor = new ScriptsExecutor(junitNotifier, config);
		executor.execute(testSuites);
	}

}
