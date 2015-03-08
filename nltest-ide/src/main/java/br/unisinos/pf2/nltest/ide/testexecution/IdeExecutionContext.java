package br.unisinos.pf2.nltest.ide.testexecution;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;

public class IdeExecutionContext {

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

	public double getPeComplete() {
		if (rootDescription == null) {
			return 0.0;
		}
		return countCompletedResults() / (double) rootDescription.testCount();
	}

	private double countCompletedResults() {
		double count = 0;
		for (ScriptResult scriptResult : results) {
			if (scriptResult.getDescription().isTest() && scriptResult.getResult() != null) {
				count++;
			}
		}
		return count;
	}

}
