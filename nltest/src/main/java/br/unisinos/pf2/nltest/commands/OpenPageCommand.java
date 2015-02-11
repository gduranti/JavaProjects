package br.unisinos.pf2.nltest.commands;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.model.Command;
import br.unisinos.pf2.nltest.model.ParameterSet;

public class OpenPageCommand implements Command {

	private static final String PARAMETER_PAGE_URL = "command.parameter.PAGE_URL";

	@Override
	public void execute(ParameterSet parameters, ExecutionContext ctx) {

		String page = parameters.getParameterValue(PARAMETER_PAGE_URL);
		ctx.getDriver().get(page);

	}

}
