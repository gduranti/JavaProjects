package br.unisinos.pf2.nltest.ide.event.events;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import br.unisinos.pf2.nltest.ide.event.EventDispatcher;
import br.unisinos.pf2.nltest.ide.filemanagement.ScriptFile;

public class TreeChangeEventAdapter implements ChangeListener<TreeItem<ScriptFile>> {

	@Override
	public void changed(ObservableValue<? extends TreeItem<ScriptFile>> observable, TreeItem<ScriptFile> oldValue, TreeItem<ScriptFile> newValue) {
		ScriptFile oldScript = oldValue == null ? null : oldValue.getValue();
		ScriptFile newScript = newValue == null ? null : newValue.getValue();
		EventDispatcher.getInstance().dispatch(new ScriptChangedEvent(oldScript, newScript));
	}

}
