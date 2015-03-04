package br.unisinos.pf2.nltest.ide.controller.thread;

import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

import org.junit.runner.Description;

import br.unisinos.pf2.nltest.ide.controller.DescriptionWrapper;
import br.unisinos.pf2.nltest.ide.testexecution.ScriptResult;

public class UpdateUiThread extends AnimationTimer {

	private static final long UPDATE_INTERVAL = TimeUnit.SECONDS.toNanos(1);

	private LongProperty lastUpdate;
	private DescriptionWrapper descriptionWrapper;
	private Button cancelButton;
	private TreeTableView<ScriptResult> treeResult;

	public UpdateUiThread(DescriptionWrapper descriptionWrapper, Button cancelButton, TreeTableView<ScriptResult> treeResult) {
		this.lastUpdate = new SimpleLongProperty();
		this.descriptionWrapper = descriptionWrapper;
		this.cancelButton = cancelButton;
		this.treeResult = treeResult;
	}

	@Override
	public void handle(long now) {
		if (now - lastUpdate.get() > UPDATE_INTERVAL) {
			System.out.println("Atualizando a UI");
			Description rootDescription = descriptionWrapper.getRootDescription();
			lastUpdate.set(now);

			if (rootDescription != null) {

				TreeItem<ScriptResult> rootTreeItem = treeResult.getRoot();
				if (rootTreeItem != null) {
					rootTreeItem.getChildren().clear();
				} else {
					ScriptResult scriptResult = new ScriptResult(rootDescription);
					descriptionWrapper.addResult(scriptResult);
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
			scriptResult = descriptionWrapper.addResult(scriptResult);

			TreeItem<ScriptResult> treeItem = new TreeItem<ScriptResult>(scriptResult);
			rootTreeItem.getChildren().add(treeItem);

			if (description.isSuite()) {
				buildSubTreeResult(description, treeItem);
			}

		}
	}
}
