package br.unisinos.pf2.nltest.runner;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import br.unisinos.pf2.nltest.executor.ScriptsExecutor;
import br.unisinos.pf2.nltest.model.TestSuite;
import br.unisinos.pf2.nltest.parser.ScriptsParser;

public class ScriptsRunner extends Runner {

	private String path;

	public ScriptsRunner(String path) {
		this.path = path;
	}

	@Override
	public Description getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run(RunNotifier junitNotifier) {

		ScriptsParser parser = new ScriptsParser();
		List<TestSuite> testSuites = parser.parse(path);

		ScriptsExecutor executor = new ScriptsExecutor(junitNotifier);
		executor.execute(testSuites);

	}

	public static void main(String[] args) {
		new ScriptsRunner("E:\\Java\\GitHub\\Unisinos\\nltest\\src\\test\\resources\\scripts-folder").run(null);
	}
}
