package br.unisinos.pf2.nltest.ide.controller.thread;

import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.event.EventListener;
import br.unisinos.pf2.nltest.ide.event.events.Event;
import br.unisinos.pf2.nltest.ide.event.events.TestExecutionFinishedEvent;
import br.unisinos.pf2.nltest.ide.testexecution.IdeExecutionContext;
import br.unisinos.pf2.nltest.ide.testexecution.ScriptResult;

public class UpdateResultUiThread extends AnimationTimer implements EventListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateResultUiThread.class);
	private static final long UPDATE_INTERVAL = TimeUnit.MILLISECONDS.toNanos(500);

	private LongProperty lastUpdate;
	private IdeExecutionContext ideExecutionContext;
	private ProgressBar progressBar;
	private TreeTableView<ScriptResult> treeResult;
	private boolean stopRequested;

	public static void start(IdeExecutionContext ideExecutionContext, ProgressBar progressBar, TreeTableView<ScriptResult> treeResult) {

		UpdateResultUiThread updateResultUiThread = new UpdateResultUiThread();
		updateResultUiThread.lastUpdate = new SimpleLongProperty();
		updateResultUiThread.ideExecutionContext = ideExecutionContext;
		updateResultUiThread.progressBar = progressBar;
		updateResultUiThread.treeResult = treeResult;
		updateResultUiThread.stopRequested = false;

		EventDispatcher.getInstance().registerListener(updateResultUiThread);
		updateResultUiThread.start();
	}

	@Override
	public void handle(long now) {

		if (stopRequested) {
			stop();
			EventDispatcher.getInstance().unregisterListener(this);

		} else if (now - lastUpdate.get() > UPDATE_INTERVAL) {

			LOGGER.debug("Updating UI at: {}", now);

			Description rootDescription = ideExecutionContext.getRootDescription();
			lastUpdate.set(now);

			progressBar.setProgress(ideExecutionContext.getPeComplete());

			if (rootDescription != null) {

				TreeItem<ScriptResult> rootTreeItem = treeResult.getRoot();
				if (rootTreeItem != null) {
					rootTreeItem.getChildren().clear();
				} else {
					ScriptResult scriptResult = new ScriptResult(rootDescription);
					ideExecutionContext.addResult(scriptResult);
					rootTreeItem = new TreeItem<ScriptResult>(scriptResult);
					treeResult.setRoot(rootTreeItem);
				}
				buildSubTreeResult(rootDescription, rootTreeItem);
			}
		}
	}

	private void buildSubTreeResult(Description rootDescription, TreeItem<ScriptResult> rootTreeItem) {
		rootTreeItem.setExpanded(true);
		for (Description description : rootDescription.getChildren()) {

			ScriptResult scriptResult = new ScriptResult(description);
			scriptResult = ideExecutionContext.addResult(scriptResult);

			TreeItem<ScriptResult> treeItem = new TreeItem<ScriptResult>(scriptResult);
			rootTreeItem.getChildren().add(treeItem);

			if (description.isSuite()) {
				buildSubTreeResult(description, treeItem);
			}
		}
	}

	@Override
	public void handleEvent(Event event) {
		if (event instanceof TestExecutionFinishedEvent) {
			stopRequested = true;
		}
	}
}
