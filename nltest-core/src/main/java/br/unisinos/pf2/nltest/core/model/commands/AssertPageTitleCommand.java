package br.unisinos.pf2.nltest.core.model.commands;

import org.junit.Assert;

import br.unisinos.pf2.nltest.core.executor.ExecutionContext;
import br.unisinos.pf2.nltest.core.model.Command;

public class AssertPageTitleCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		String expectedTitle = getParameterValue(0);
		Assert.assertEquals(expectedTitle, ctx.getDriver().getTitle());
	}

}
