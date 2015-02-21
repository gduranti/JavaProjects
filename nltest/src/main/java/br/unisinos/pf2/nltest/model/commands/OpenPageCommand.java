package br.unisinos.pf2.nltest.model.commands;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.model.Command;

public class OpenPageCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		String page = getParameterValue(0);
		ctx.getDriver().get(page);

	}

}
