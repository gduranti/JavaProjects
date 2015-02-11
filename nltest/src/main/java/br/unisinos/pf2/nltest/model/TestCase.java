package br.unisinos.pf2.nltest.model;

import java.util.ArrayList;
import java.util.List;

import br.unisinos.pf2.nltest.executor.ExecutionContext;

public class TestCase implements Executable {

	private String description;
	private List<Command> commands;
	private List<ParameterSet> parameters;

	public TestCase(String description) {
		this.description = description;
		this.commands = new ArrayList<>();
		this.parameters = new ArrayList<>();
	}

	public void addCommand(Command commandToAdd) {
		commands.add(commandToAdd);
	}

	public void addParameter(ParameterSet parameterSetToAdd) {
		parameters.add(parameterSetToAdd);
	}

	@Override
	public void execute(ExecutionContext ctx) {
		for (ParameterSet parameterSet : parameters) {
			for (Command command : commands) {
				command.execute(parameterSet, ctx);
			}
		}
	}

}
