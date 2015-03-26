package br.unisinos.pf2.nltest.core.model.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.unisinos.pf2.nltest.core.executor.ExecutionContext;
import br.unisinos.pf2.nltest.core.model.Command;

public class ClickLinkCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		String linkText = getParameterValue(0);
		WebElement field = ctx.getDriver().findElement(By.linkText(linkText));
		field.click();

	}

}
