package br.unisinos.pf2.nltest.model.commands;

import org.openqa.selenium.Alert;

import br.unisinos.pf2.nltest.executor.ExecutionContext;
import br.unisinos.pf2.nltest.model.Command;

public class AcceptAlertCommand extends Command {

	@Override
	public void execute(ExecutionContext ctx) {

		Alert alert = ctx.getDriver().switchTo().alert();
		alert.accept();
	}

}
