package br.unisinos.pf2.nltest.commands;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.model.Command;

public class OpenPageCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		System.out.println("Executing command open page");

		String page = getParameterValue(0);
		ctx.getDriver().get(page);

	}

}
