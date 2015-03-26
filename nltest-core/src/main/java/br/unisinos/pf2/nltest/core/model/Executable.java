package br.unisinos.pf2.nltest.core.model;

import org.junit.runner.Description;

import br.unisinos.pf2.nltest.core.executor.ExecutionContext;

public interface Executable extends Parseable {

	void execute(ExecutionContext ctx);

	Description getDescription();

}
