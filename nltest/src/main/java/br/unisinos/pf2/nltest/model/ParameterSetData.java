package br.unisinos.pf2.nltest.model;

import java.util.Arrays;
import java.util.List;

public class ParameterSetData implements Parseable {

	private List<String> values;

	@Override
	public void init(String... args) {
		// TODO tratar args vazio
		values = Arrays.asList(args);
	}

	public int indexOf(String value) {
		return values.indexOf(value);
	}

	public String get(int index) {
		return values.get(index);
	}

	@Override
	public String toString() {
		return values.toString();
	}

}
