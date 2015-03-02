package br.unisinos.pf2.nltest.ide.filemanagement;

import java.io.File;
import java.io.IOException;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import br.unisinos.pf2.nltest.ide.exceptions.NLTestIdeException;

public class ScriptFileWritter {

	public void write(TreeView<ScriptFile> fileTree, File rootProjectDirectory) {

		TreeItem<ScriptFile> currentTreeItem = fileTree.getRoot();
		File currentDirectory = rootProjectDirectory;

		try {
			verifyNode(currentTreeItem, currentDirectory);
		} catch (IOException e) {
			throw new NLTestIdeException("Ocorreu um erro ao gravar os arquivos.", e);
		}
	}

	private void verifyNode(TreeItem<ScriptFile> currentTreeItem, File currentDirectory) throws IOException {
		for (TreeItem<ScriptFile> treeItem : currentTreeItem.getChildren()) {

			boolean updated = false;

			ScriptFile scriptFile = treeItem.getValue();
			File matchingFile = null;

			for (File file : currentDirectory.listFiles()) {
				if (file.getName().equals(scriptFile.getName())) {
					matchingFile = file;
					updateFile(file, scriptFile);
					updated = true;
				}
			}

			if (!updated) {
				matchingFile = createFile(currentDirectory, scriptFile);
			}

			if (scriptFile.isDirectory()) {
				verifyNode(treeItem, matchingFile);
			}
		}
	}

	private File createFile(File currentDirectory, ScriptFile scriptFile) throws IOException {

		String fileName = scriptFile.getName();
		if (!scriptFile.isDirectory()) {
			fileName = StringUtils.appendIfMissing(scriptFile.getName(), ".nlt");
		}

		String pathname = currentDirectory.getAbsolutePath() + "/" + fileName;

		File file = new File(pathname);

		if (scriptFile.isDirectory()) {
			file.mkdir();
		} else {
			file.createNewFile();
			FileUtils.write(file, scriptFile.getScriptContent());
		}

		return file;
	}

	private void updateFile(File file, ScriptFile value) throws IOException {
		if (!file.isDirectory()) {
			FileUtils.write(file, value.getScriptContent());
		}
	}

}
