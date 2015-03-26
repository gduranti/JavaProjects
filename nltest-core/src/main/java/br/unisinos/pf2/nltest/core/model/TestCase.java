package br.unisinos.pf2.nltest.core.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import br.unisinos.pf2.nltest.core.executor.ExecutionContext;

public class TestCase implements Executable, Cloneable {

	private Description description;
	private List<Command> commands;

	@Override
	public void init(String baseScript, String[] args) {
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

		for (Command command : commands) {
			RunNotifier notifier = ctx.getNotifier();
			try {
				notifier.fireTestStarted(command.getDescription());
				command.execute(ctx);
				notifier.fireTestFinished(command.getDescription());
			} catch (Throwable e) {
				notifier.fireTestFailure(new Failure(command.getDescription(), e));

				// Quando um comando do caso de teste falha, os comandos
				// seguites nao sao executados
				return;
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

	@Override
	public String toString() {
		return description.getDisplayName();
	}

}
