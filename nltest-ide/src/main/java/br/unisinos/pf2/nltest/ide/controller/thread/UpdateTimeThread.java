package br.unisinos.pf2.nltest.ide.controller.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

public class UpdateTimeThread extends Task<Void> {

	private boolean minutes;
	private Label label;

	public static void start(Label sysdateLabel) {
		Thread th = new Thread(new UpdateTimeThread(sysdateLabel));
		th.setDaemon(true);
		th.start();
	}

	private UpdateTimeThread(Label label) {
		this.label = label;
		this.minutes = false;
	}

	@Override
	public Void call() throws Exception {
		while (true) {

			LocalDateTime now = LocalDateTime.now();

			// System.out.println("ssss " + now);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
					label.setText(formatter.format(now));
				}
			});

			if (minutes) {
				Thread.sleep(TimeUnit.SECONDS.toMillis(60));
			} else if (now.getSecond() == 00) {
				minutes = true;
				Thread.sleep(TimeUnit.SECONDS.toMillis(60));
			} else {
				Thread.sleep(TimeUnit.SECONDS.toMillis(1));
			}
		}
	}

}