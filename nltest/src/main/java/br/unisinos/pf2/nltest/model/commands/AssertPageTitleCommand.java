package br.unisinos.pf2.nltest.model.commands;

import org.junit.Assert;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.model.Command;

public class AssertPageTitleCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		String expectedTitle = getParameterValue(0);
		Assert.assertEquals(expectedTitle, ctx.getDriver().getTitle());
	}

}
