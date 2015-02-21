package br.unisinos.pf2.nltest.model;

import br.unisinos.pf2.nltest.executor.ExecutionContext;

public interface Executable {

	void init(String... args);

	void execute(ExecutionContext ctx);

}
