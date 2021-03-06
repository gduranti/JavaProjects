package br.unisinos.pf2.nltest.core.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.runner.Description;

import br.unisinos.pf2.nltest.core.parser.UUIDGenerator;

public abstract class Command implements Executable {

	private Description description;
	private List<String> simpleParameters;
	private ParameterSet parameterSet;
	private String baseScript;

	@Override
	public void init(String baseScript, String[] args) {
		this.baseScript = baseScript;
		this.simpleParameters = Arrays.asList(args);
	}

	@Override
	public Description getDescription() {
		if (description == null) {
			description = buildDescription();
		}
		return description;
	}

	protected String getParameterValue(int index) {
		String value = simpleParameters.get(index);

		if (isVariableParameter(value)) {
			value = StringUtils.removeStart(value, "<");
			value = StringUtils.removeEnd(value, ">");
			value = parameterSet.getValue(value);
		}

		return value;
	}

	private boolean isVariableParameter(String value) {
		return value.startsWith("<") && value.endsWith(">");
	}

	public Command copyWith(ParameterSet parameterSet) {
		try {
			Command copiedCommand = this.getClass().newInstance();
			copiedCommand.simpleParameters = new ArrayList<>(this.simpleParameters);
			copiedCommand.baseScript = baseScript;
			copiedCommand.parameterSet = parameterSet;
			copiedCommand.description = copiedCommand.buildDescription();
			return copiedCommand;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Erro inesperado ao gerar script.", e);
		}
	}

	private Description buildDescription() {
		String display = getDisplayCommand();
		return Description.createTestDescription(getClass().getName(), display, UUIDGenerator.getInstace().next());
	}

	public String getDisplayCommand() {
		String display = baseScript;
		for (int i = 0; i < StringUtils.countMatches(baseScript, "(.*)"); i++) {
			display = display.replaceFirst("\\(\\.\\*\\)", getParameterValue(i));
		}
		return display;
	}

	@Override
	public String toString() {
		String format = "%s. SimpleParameters: %s. ParameterSet: %s.";
		return String.format(format, getClass().getSimpleName(), simpleParameters, parameterSet);
	}

}
