package br.unisinos.pf2.nltest.core.model.commands;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.unisinos.pf2.nltest.core.executor.ExecutionContext;
import br.unisinos.pf2.nltest.core.model.Command;

public class AssertFieldValueCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		String id = getParameterValue(0);
		WebElement field = ctx.getDriver().findElement(By.id(id));

		String expectedValue = getParameterValue(1);
		Assert.assertEquals(expectedValue, field.getText());

	}

}
