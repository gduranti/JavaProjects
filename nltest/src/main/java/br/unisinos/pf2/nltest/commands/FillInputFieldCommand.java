package br.unisinos.pf2.nltest.commands;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.model.Command;
import br.unisinos.pf2.nltest.model.ParameterSet;

public class FillInputFieldCommand implements Command {

	private static final String PARAMETER_FIELD_ID = "FillInputFieldCommand.FIELD_ID";
	private static final String PARAMETER_FIELD_VALUE = "FillInputFieldCommand.FIELD_VALUE";

	@Override
	public void execute(ParameterSet parameters, ExecutionContext ctx) {

		String id = parameters.getParameterValue(PARAMETER_FIELD_ID);
		WebElement field = ctx.getDriver().findElement(By.id(id));

		String keysToSend = parameters.getParameterValue(PARAMETER_FIELD_VALUE);
		field.sendKeys(keysToSend);

	}

}
