package br.unisinos.pf2.nltest.model;

import br.unisinos.pf2.nltest.executor.ExecutionContext;

public interface Command {

	void execute(ParameterSet parameters, ExecutionContext ctx);

}
