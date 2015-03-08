package br.unisinos.pf2.nltest.ide.controller.thread;

import java.io.File;

import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.event.events.TestExecutionFinishedEvent;
import br.unisinos.pf2.nltest.ide.event.events.TestExecutionStartedEvent;
import br.unisinos.pf2.nltest.ide.testexecution.IdeExecutionContext;
import br.unisinos.pf2.nltest.ide.testexecution.JUnitExecutor;

public class ExecuteTestsThread implements Runnable {

	private IdeExecutionContext ideExecutionContext;
	private File file;

	private ExecuteTestsThread(IdeExecutionContext ideExecutionContext, File file) {
		this.ideExecutionContext = ideExecutionContext;
		this.file = file;
	}

	public static void start(IdeExecutionContext ideExecutionContext, File file) {
		ExecuteTestsThread executeTestsThread = new ExecuteTestsThread(ideExecutionContext, file);
		Thread thread = new Thread(executeTestsThread);
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void run() {
		EventDispatcher.getInstance().dispatch(new TestExecutionStartedEvent());
		JUnitExecutor executor = new JUnitExecutor();
		executor.execute(ideExecutionContext, file);
		EventDispatcher.getInstance().dispatch(new TestExecutionFinishedEvent());
	}

}
