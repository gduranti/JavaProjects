package br.unisinos.pf2.nltest.model;

import java.util.Arrays;
import java.util.List;

public class ParameterSetData implements Parseable {

	private List<String> headers;
	private List<String> values;

	@Override
	public void init(String... args) {
		// TODO tratar args vazio
		values = Arrays.asList(args);
	}

}
