package br.unisinos.pf2.nltest.core.model.commands;

public class AssertFieldIsEnabledCommand extends AssertFieldStateCommand {

	@Override
	protected boolean getExpectedState() {
		return true;
	}

}
