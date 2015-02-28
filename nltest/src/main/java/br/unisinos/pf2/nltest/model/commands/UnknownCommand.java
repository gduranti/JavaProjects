package br.unisinos.pf2.nltest.model.commands;

import org.junit.Assert;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.model.Command;

public class UnknownCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {
		Assert.fail("N�o foi poss�vel interpretar o comando: " + getParameterValue(0));
	}

}
