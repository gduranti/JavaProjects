package br.unisinos.pf2.nltest.model.commands;

import org.junit.Assert;
import org.openqa.selenium.Alert;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.model.Command;

public class AssertAlertTextCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		String expectedAlertText = getParameterValue(0);
		Alert alert = ctx.getDriver().switchTo().alert();
		Assert.assertEquals(expectedAlertText, alert.getText());
	}

}
