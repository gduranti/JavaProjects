package br.unisinos.pf2.nltest.ide.controller.thread;

import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.event.events.TestExecutionFinishedEvent;
import br.unisinos.pf2.nltest.ide.event.events.TestExecutionStartedEvent;
import br.unisinos.pf2.nltest.ide.testexecution.IdeExecutionContext;
import br.unisinos.pf2.nltest.ide.testexecution.JUnitExecutor;

public class ExecuteTestsThread implements Runnable {

	private IdeExecutionContext ideExecutionContext;

	public static void start(IdeExecutionContext ideExecutionContext) {
		ExecuteTestsThread executeTestsThread = new ExecuteTestsThread();
		executeTestsThread.ideExecutionContext = ideExecutionContext;

		Thread thread = new Thread(executeTestsThread);
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void run() {
		EventDispatcher.getInstance().dispatch(new TestExecutionStartedEvent());
		JUnitExecutor executor = new JUnitExecutor();
		executor.execute(ideExecutionContext);
		EventDispatcher.getInstance().dispatch(new TestExecutionFinishedEvent());
	}

}
