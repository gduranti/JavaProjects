package br.unisinos.pf2.nltest.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.unisinos.pf2.nltest.model.Command;
import br.unisinos.pf2.nltest.model.ParameterSet;
import br.unisinos.pf2.nltest.model.ParameterSetData;
import br.unisinos.pf2.nltest.model.TestCase;

public class TestCaseBuilder {

	private TestCase mainTestCase;
	private List<Command> commands;
	private List<ParameterSetData> parameterSetDataList;
	private boolean acceptParameterSet;

	public void setTestCase(TestCase testCase) {
		this.mainTestCase = testCase;
		reset();
	}

	private void reset() {
		commands = new ArrayList<>();
		parameterSetDataList = new ArrayList<>();
		acceptParameterSet = false;
	}

	public void addCommand(Command command) {
		commands.add(command);
	}

	public void withParameterSet(boolean acceptParameterSet) {
		this.acceptParameterSet = acceptParameterSet;
	}

	public void addParameterSetData(ParameterSetData parameterSetData) {
		if (!acceptParameterSet) {
			// TODO
		}
		parameterSetDataList.add(parameterSetData);
	}

	private boolean hasElementToBuild() {
		return mainTestCase != null && !commands.isEmpty();
	}

	public List<TestCase> build() {

		if (!hasElementToBuild()) {
			return Collections.emptyList();
		}

		validateBuild();

		if (parameterSetDataList.isEmpty()) {
			return buildSingleTestCase();
		} else {
			return buildMultipleTestCase();
		}
	}

	private List<TestCase> buildSingleTestCase() {
		for (Command command : commands) {
			mainTestCase.addCommand(command);
		}
		return Arrays.asList(mainTestCase);
	}

	private void validateBuild() {
		// TODO Auto-generated method stub

	}

	private List<TestCase> buildMultipleTestCase() {
		List<TestCase> testCaseList = new ArrayList<>();
		ParameterSetData parameterSetHeader = parameterSetDataList.remove(0);

		int index = 1;

		for (ParameterSetData parameterSetValues : parameterSetDataList) {
			TestCase coppiedTestCase = mainTestCase.copy(index++);
			for (Command command : commands) {
				Command commandWithParameters = command.copyWith(new ParameterSet(parameterSetHeader, parameterSetValues));
				coppiedTestCase.addCommand(commandWithParameters);
			}
			testCaseList.add(coppiedTestCase);
		}

		return testCaseList;
	}

}
