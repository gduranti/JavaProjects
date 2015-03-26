package br.unisinos.pf2.nltest.core.model.commands;

import org.openqa.selenium.Alert;

import br.unisinos.pf2.nltest.core.executor.ExecutionContext;
import br.unisinos.pf2.nltest.core.model.Command;

public class AcceptAlertCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		Alert alert = ctx.getDriver().switchTo().alert();
		alert.accept();
	}

}
