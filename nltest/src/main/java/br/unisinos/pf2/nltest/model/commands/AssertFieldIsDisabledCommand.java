package br.unisinos.pf2.nltest.model.commands;

public class AssertFieldIsDisabledCommand extends AssertFieldStateCommand {

	@Override
	protected boolean getExpectedState() {
		return false;
	}

}
