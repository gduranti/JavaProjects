package br.unisinos.pf2.nltest.model.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.model.Command;

public class ClickButtonCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		String value = getParameterValue(0);

		WebElement field;
		try {
			field = ctx.getDriver().findElement(By.xpath("//input[@type='submit' and @value='" + value + "']"));
		} catch (NoSuchElementException e) {
			field = ctx.getDriver().findElement(By.xpath("//input[@type='button' and @value='" + value + "']"));
		}
		field.click();

	}

}
