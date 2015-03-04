package br.unisinos.pf2.nltest.ide.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;

import br.unisinos.pf2.nltest.ide.testexecution.ScriptResult;

public class DescriptionWrapper {

	private Description rootDescription;
	private List<ScriptResult> results = new ArrayList<>();

	public Description getRootDescription() {
		return rootDescription;
	}

	public void setRootDescription(Description rootDescription) {
		this.rootDescription = rootDescription;
	}

	public ScriptResult getResult(Description description) {
		for (ScriptResult scriptResult : results) {
			if (scriptResult.getDescription().equals(description)) {
				return scriptResult;
			}
		}
		return null;
	}

	public ScriptResult addResult(ScriptResult scriptResult) {
		ScriptResult equalityResult = getResult(scriptResult.getDescription());
		if (equalityResult != null) {
			return equalityResult;
		} else {
			results.add(scriptResult);
			return scriptResult;
		}
	}

	public List<ScriptResult> getResults() {
		return results;
	}

}
