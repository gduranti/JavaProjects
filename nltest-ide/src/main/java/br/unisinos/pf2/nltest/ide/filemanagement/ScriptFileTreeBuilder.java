package br.unisinos.pf2.nltest.ide.filemanagement;

import java.io.File;
import java.io.IOException;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import org.apache.commons.io.FileUtils;

import br.unisinos.pf2.nltest.ide.exceptions.NLTestIdeException;

public class ScriptFileTreeBuilder {

	public void build(TreeView<ScriptFile> fileTree, File rootDirectory) {

		TreeItem<ScriptFile> rootTreeItem = new TreeItem<>(ScriptFile.createDirectory(rootDirectory.getName()));
		rootTreeItem.setExpanded(true);
		fileTree.setRoot(rootTreeItem);
		buildTree(rootDirectory, rootTreeItem);
	}

	private void buildTree(File directory, TreeItem<ScriptFile> rootTreeItem) {
		for (File file : directory.listFiles()) {

			if (file.isDirectory()) {
				TreeItem<ScriptFile> treeItem = new TreeItem<>(ScriptFile.createDirectory(file.getName()));
				treeItem.setExpanded(true);
				rootTreeItem.getChildren().add(treeItem);
				buildTree(file, treeItem);
			} else {
				try {
					String scriptContent = FileUtils.readFileToString(file);
					TreeItem<ScriptFile> treeItem = new TreeItem<>(ScriptFile.createFile(file.getName(), scriptContent));
					rootTreeItem.getChildren().add(treeItem);
				} catch (IOException e) {
					throw new NLTestIdeException("Ocorreu um erro inesperado ao ler o arquivo " + file.getName(), e);
				}
			}
		}
	}

}
