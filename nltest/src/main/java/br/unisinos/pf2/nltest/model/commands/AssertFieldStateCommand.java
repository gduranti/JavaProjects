package br.unisinos.pf2.nltest.model.commands;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.model.Command;

public abstract class AssertFieldStateCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		String id = getParameterValue(0);
		WebElement field = ctx.getDriver().findElement(By.id(id));
		Assert.assertEquals(getExpectedState(), field.isEnabled());
	}

	protected abstract boolean getExpectedState();

}
