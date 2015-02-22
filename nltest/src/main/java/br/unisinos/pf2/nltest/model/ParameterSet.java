package br.unisinos.pf2.nltest.model;

import java.util.Map;

public class ParameterSet {

	private ParameterSetData header;
	private ParameterSetData values;

	public ParameterSet(ParameterSetData header, ParameterSetData values) {
		this.header = header;
		this.values = values;
	}

	public Map<String, String> toMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
