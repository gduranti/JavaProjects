package br.unisinos.pf2.nltest.core.parser;

import java.util.concurrent.atomic.AtomicInteger;

public class UUIDGenerator {

	private AtomicInteger uuid;
	private static UUIDGenerator instace;

	public synchronized static UUIDGenerator getInstace() {
		if (instace == null) {
			instace = new UUIDGenerator();
			instace.reset();
		}
		return instace;
	}

	public void reset() {
		uuid = new AtomicInteger(1);
	}

	public int next() {
		return uuid.getAndIncrement();
	}

}
