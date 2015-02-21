package br.unisinos.pf2.nltest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.unisinos.pf2.nltest.executor.ExecutionContext;

public class TestCase implements Executable {

	private String description;
	private List<Command> commands;
	private List<Map<String, String>> parametersSets;

	@Override
	public void init(String... args) {
		// TODO tratar args vazio
		this.description = args[0];
		this.commands = new ArrayList<>();
		this.parametersSets = new ArrayList<>();
	}

	public void addCommand(Command commandToAdd) {
		commands.add(commandToAdd);
	}

	public Map<String, String> newParameterSet() {
		Map<String, String> newParameterSet = new HashMap<String, String>();
		parametersSets.add(newParameterSet);
		return newParameterSet;
	}

	@Override
	public void execute(ExecutionContext ctx) {

		System.out.println("Executing test case " + description);

		if (parametersSets.isEmpty()) {
			executeCommands(ctx, null);
		} else {
			for (Map<String, String> parameterSet : parametersSets) {
				System.out.println("Executing test case with a parameter set: " + description);
				executeCommands(ctx, parameterSet);
			}
		}
	}

	private void executeCommands(ExecutionContext ctx, Map<String, String> parameterSet) {
		for (Command command : commands) {
			command.setCurrentVariableParametersSet(parameterSet);
			command.execute(ctx);
		}
	}

}
