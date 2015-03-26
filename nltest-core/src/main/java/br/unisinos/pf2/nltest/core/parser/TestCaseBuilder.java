package br.unisinos.pf2.nltest.core.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.unisinos.pf2.nltest.core.model.Command;
import br.unisinos.pf2.nltest.core.model.ParameterSet;
import br.unisinos.pf2.nltest.core.model.ParameterSetData;
import br.unisinos.pf2.nltest.core.model.TestCase;

public class TestCaseBuilder {

	private TestCase mainTestCase;
	private List<Command> commands;
	private List<ParameterSetData> parameterSetDataList;
	private boolean acceptParameterSet;

	public TestCaseBuilder() {
		reset();
	}

	public void setTestCase(TestCase testCase) {
		this.mainTestCase = testCase;
		reset();
	}

	public void reset() {
		commands = new ArrayList<>();
		parameterSetDataList = new ArrayList<>();
		acceptParameterSet = false;
	}

	public void addCommand(Command command) {
		commands.add(command);
	}

	public void withParameterSet() {
		this.acceptParameterSet = true;
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
