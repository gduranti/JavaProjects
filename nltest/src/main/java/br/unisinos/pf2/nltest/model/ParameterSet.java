package br.unisinos.pf2.nltest.model;

public class ParameterSet {

	private ParameterSetData header;
	private ParameterSetData values;

	public ParameterSet(ParameterSetData header, ParameterSetData values) {
		this.header = header;
		this.values = values;
	}

	public String getValue(String header) {
		int i = this.header.indexOf(header);
		return values.get(i);
	}

}
