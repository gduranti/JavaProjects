package br.unisinos.pf2.nltest.model;

import java.util.HashMap;
import java.util.Map;

public class ParameterSet {

	private Map<String, String> parameters;

	private Map<String, String> getParameters() {
		if (parameters == null) {
			parameters = new HashMap<>();
		}
		return parameters;
	}

	public void addParameter(String name, String value) {
		getParameters().put(name, value);
	}

	public String getParameterValue(String name) {
		return getParameters().get(name);
	}

}
