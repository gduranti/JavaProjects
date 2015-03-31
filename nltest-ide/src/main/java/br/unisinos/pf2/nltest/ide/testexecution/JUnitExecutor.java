package br.unisinos.pf2.nltest.ide.testexecution;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.runner.JUnitCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unisinos.pf2.nltest.core.executor.Browser;
import br.unisinos.pf2.nltest.ide.controller.IdePrefs;
import br.unisinos.pf2.nltest.ide.controller.thread.ExecuteTestsThread;
import br.unisinos.pf2.nltest.ide.exceptions.NLTestIdeException;

public class JUnitExecutor {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteTestsThread.class);

	public void execute(IdeExecutionContext ideExecutionContext) {
		LOGGER.debug("Starting test execution...");

		initBrowserSettings();

		JUnitCore jUnitCore = new JUnitCore();
		jUnitCore.addListener(new LoggingRunListener());
		jUnitCore.addListener(new ExecutionRunListener(ideExecutionContext));
		jUnitCore.run(IdeTestConfigurator.class);

		LOGGER.debug("Test execution finished.");
	}

	private void initBrowserSettings() {

		if (IdePrefs.getDefaultBroser() == Browser.FIREFOX) {
			return;
		}

		String driverPath = extractDefaultDriver();
		String driverName = IdePrefs.getDefaultBroser().name().toLowerCase();
		System.setProperty("webdriver." + driverName + ".driver", driverPath);
	}

	private String extractDefaultDriver() {
		String browserName = IdePrefs.getDefaultBroser().name();
		try {
			String driverName = browserName.toLowerCase() + "driver.exe";
			String driverFolderPath = System.getProperty("user.dir") + "\\app\\webdrivers";
			String driverPath = driverFolderPath + "\\" + driverName;
			File driver = new File(driverPath);

			if (!driver.exists()) {
				File dir = new File(driverFolderPath);
				dir.mkdirs();
				extractDriver(driver, driverName);
			}
			return driverPath;

		} catch (Exception e) {
			String msg = String.format("Ocorreu um erro ao extrair o driver do %s. Por favor, configure a NLTest para utilizar o Firefox.",
					browserName);
			throw new NLTestIdeException(msg, e);
		}
	}

	private void extractDriver(File driver, String driverName) throws IOException {

		URL resource = getClass().getResource("/webdrivers/" + driverName);

		InputStream openStream = resource.openStream();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(openStream);

		FileOutputStream fos = new FileOutputStream(driver);
		fos.write(baos.toByteArray());

		openStream.close();
		baos.close();
		fos.flush();
		fos.close();

		System.out.println("Arquivo " + driverName + " extraido para " + driver.getPath());
	}

}
