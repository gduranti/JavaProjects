import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import br.unisinos.pf2.nltest.ide.testexecution.JUnitExecutor;

public class Main {

	public static void main(String[] args) throws URISyntaxException {
		URL resource = JUnitExecutor.class.getResource("../../../../../../webdrivers/chromedriver.exe");
		System.out.println(resource);
		System.out.println(new File(resource.toURI()).exists());
	}

}