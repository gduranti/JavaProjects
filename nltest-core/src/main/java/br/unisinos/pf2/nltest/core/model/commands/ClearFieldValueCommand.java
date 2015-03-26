package br.unisinos.pf2.nltest.core.model.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.unisinos.pf2.nltest.core.executor.ExecutionContext;
import br.unisinos.pf2.nltest.core.model.Command;

public class ClearFieldValueCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		String id = getParameterValue(0);
		WebElement field = ctx.getDriver().findElement(By.id(id));
		field.clear();

	}

}
