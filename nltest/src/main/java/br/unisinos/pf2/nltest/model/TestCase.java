package br.unisinos.pf2.nltest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.Description;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.runner.TestCaseNotifier;

public class TestCase implements Executable {

	private Description description;
	private List<Command> commands;
	private List<Map<String, String>> parametersSets;

	@Override
	public void init(String... args) {
		// TODO tratar args vazio
		this.description = Description.createSuiteDescription(args[0]);
		this.commands = new ArrayList<>();
		this.parametersSets = new ArrayList<>();
	}

	public void addCommand(Command commandToAdd) {
		commands.add(commandToAdd);
		description.addChild(commandToAdd.getDescription());
	}

	public Map<String, String> newParameterSet() {
		Map<String, String> newParameterSet = new HashMap<String, String>();
		parametersSets.add(newParameterSet);
		return newParameterSet;
	}

	@Override
	public Description getDescription() {
		return description;
	}

	public List<Command> peekCommands() {
		return new ArrayList<Command>(commands);
	}

	public List<Map<String, String>> peekParametersSets() {
		return new ArrayList<Map<String, String>>(parametersSets);
	}

	@Override
	public void execute(ExecutionContext ctx) {

		System.out.println("Executing test case " + description.getDisplayName());

		if (parametersSets.isEmpty()) {
			executeCommands(ctx, null);
		} else {
			for (Map<String, String> parameterSet : parametersSets) {
				System.out.println("Executing test case with a parameter set: " + description.getDisplayName());
				executeCommands(ctx, parameterSet);
			}
		}
	}

	private void executeCommands(ExecutionContext ctx, Map<String, String> parameterSet) {
		for (Command command : commands) {
			TestCaseNotifier notifier = ctx.getNotifier();
			try {
				notifier.setCurrentDescription(command.getDescription());
				notifier.started();
				command.setCurrentVariableParametersSet(parameterSet);
				command.execute(ctx);
				notifier.succesful();
			} catch (Throwable e) {
				notifier.failed(e);
			}
		}
	}

}
