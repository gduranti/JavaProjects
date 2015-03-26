package br.unisinos.pf2.nltest.core.model;

public class ParameterSet {

	private ParameterSetData headers;
	private ParameterSetData values;

	public ParameterSet(ParameterSetData headers, ParameterSetData values) {
		this.headers = headers;
		this.values = values;
	}

	public String getValue(String header) {
		int i = this.headers.indexOf(header);
		return values.get(i);
	}

	@Override
	public String toString() {
		return String.format("{%s}-{%s}", headers, values);
	}

}
