package br.unisinos.pf2.nltest.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.runner.TestCaseNotifier;

public class TestCase implements Executable, Cloneable {

	private Description description;
	private List<Command> commands;

	@Override
	public void init(String... args) {
		// TODO tratar args vazio
		this.description = Description.createSuiteDescription(args[0]);
		this.commands = new ArrayList<>();
	}

	public void addCommand(Command commandToAdd) {
		commands.add(commandToAdd);
		description.addChild(commandToAdd.getDescription());
	}

	@Override
	public Description getDescription() {
		return description;
	}

	@Override
	public void execute(ExecutionContext ctx) {

		System.out.println("Executing test case " + description.getDisplayName());

		for (Command command : commands) {
			TestCaseNotifier notifier = ctx.getNotifier();
			try {
				notifier.setCurrentDescription(command.getDescription());
				notifier.started();
				command.execute(ctx);
				notifier.succesful();
			} catch (Throwable e) {
				notifier.failed(e);
			}
		}
	}

	public TestCase copy(int index) {
		TestCase newTestCase = new TestCase();
		String newDescriptionName = String.format("%s (Conjunto de dados %s)", this.description.getDisplayName(), index);
		newTestCase.description = Description.createSuiteDescription(newDescriptionName);
		newTestCase.commands = new ArrayList<>();
		return newTestCase;
	}

}
