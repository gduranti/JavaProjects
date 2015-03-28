package br.unisinos.pf2.nltest.core.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.unisinos.pf2.nltest.core.model.ParameterSetData;
import br.unisinos.pf2.nltest.core.model.ParameterSetMaker;
import br.unisinos.pf2.nltest.core.model.Parseable;

public class CommandTranslatorTest {

	private CommandTranslator commandTranslator;

	@Before
	public void setUp() {
		commandTranslator = new CommandTranslator();
	}

	@Test
	public void test_ParameterSetMaker() {
		Parseable parseable = commandTranslator.interpret("Dados:");
		assertEquals(ParameterSetMaker.class, parseable.getClass());
	}

	@Test
	public void test_ParameterSetData() {
		Parseable parseable = commandTranslator.interpret("| Gabriel | Bruna |");
		assertEquals(ParameterSetData.class, parseable.getClass());

		ParameterSetData parameterSetData = (ParameterSetData) parseable;
		assertEquals("Gabriel", parameterSetData.get(0));
		assertEquals("Bruna", parameterSetData.get(1));
	}

	@Test
	public void test_ParameterSetData_Com_Espaco() {
		Parseable parseable = commandTranslator.interpret("| Gabriel Duranti | Bruna Oliveira |");
		assertEquals(ParameterSetData.class, parseable.getClass());

		ParameterSetData parameterSetData = (ParameterSetData) parseable;
		assertEquals("Gabriel Duranti", parameterSetData.get(0));
		assertEquals("Bruna Oliveira", parameterSetData.get(1));
	}

	@Test
	public void test_ParameterSetData_Com_Caractere_Especial() {
		Parseable parseable = commandTranslator.interpret("| Júnior & Tavares Rest@urantes | Bar da Preta |");
		assertEquals(ParameterSetData.class, parseable.getClass());

		ParameterSetData parameterSetData = (ParameterSetData) parseable;
		assertEquals("Júnior & Tavares Rest@urantes", parameterSetData.get(0));
		assertEquals("Bar da Preta", parameterSetData.get(1));
	}

}
