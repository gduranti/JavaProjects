package com.unisinos.processoDeTeste.selenium.procAval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.unisinos.processoDeTeste.selenium.procAval.pages.AvaliadorPage;
import com.unisinos.processoDeTeste.selenium.procAval.pages.IndexPage;

public class TestesAvaliador {

	private static final String BASE_URL = "http://procaval.azurewebsites.net";

	private WebDriver driver;

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Abre a página do site
		driver.get(BASE_URL);

		// Efetua login no sistema
		ProcAvalLogin procAvalLogin = new ProcAvalLogin(driver);
		procAvalLogin.login();
	}

	@Test
	public void testInserirAvaliadorSemNome() throws Exception {

		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.getMenuAvaliador().click();

		AvaliadorPage avaliadorPage = PageFactory.initElements(driver, AvaliadorPage.class);
		avaliadorPage.getBotaoNovo().click();

		FluentWait<WebDriver> wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(avaliadorPage.getNome()));

		avaliadorPage.getCpf().sendKeys("12345678901");
		avaliadorPage.getBotaoSalvar().click();

		assertEquals("Obrigatório", avaliadorPage.getMsgNomeObrigatorio().getText());
		assertFalse(isElementPresent(By.xpath("//span[@for='Cpf']")));
	}

	@Test
	public void testInserirAvaliadorSemCpf() throws Exception {

		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.getMenuAvaliador().click();

		AvaliadorPage avaliadorPage = PageFactory.initElements(driver, AvaliadorPage.class);
		avaliadorPage.getBotaoNovo().click();

		FluentWait<WebDriver> wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(avaliadorPage.getNome()));

		avaliadorPage.getNome().sendKeys("Um nome qualquer");
		avaliadorPage.getBotaoSalvar().click();

		assertEquals("Obrigatório", avaliadorPage.getMsgCpfObrigatorio().getText());
		assertFalse(isElementPresent(By.xpath("//span[@for='Nome']")));
	}

	@Test
	public void testInserirAvaliadorComSucesso() throws Exception {

		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.getMenuAvaliador().click();

		AvaliadorPage avaliadorPage = PageFactory.initElements(driver, AvaliadorPage.class);

		Integer numeroDeAvaliadoresInicial = getNumeroDeAvaliadores();

		avaliadorPage.getBotaoNovo().click();

		FluentWait<WebDriver> wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(avaliadorPage.getNome()));

		avaliadorPage.getNome().sendKeys("Outro nome qualquer");
		avaliadorPage.getCpf().sendKeys("00100100101");
		avaliadorPage.getBotaoSalvar().click();

		wait.until(ExpectedConditions.visibilityOf(avaliadorPage.getMessagemGeral()));
		assertEquals("Avaliador incluído com sucesso", avaliadorPage.getMessagemGeral().getText());

		Integer numeroDeAvaliadoresFinal = getNumeroDeAvaliadores();
		assertEquals(numeroDeAvaliadoresInicial + 1, numeroDeAvaliadoresFinal, 0);
	}

	@Test
	public void testExcluirAvaliadorComSucesso() throws Exception {

		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.getMenuAvaliador().click();

		AvaliadorPage avaliadorPage = PageFactory.initElements(driver, AvaliadorPage.class);

		Integer numeroDeAvaliadoresInicial = getNumeroDeAvaliadores();

		WebElement botaoExcluirUltimoAvaliador = avaliadorPage.getBotoesExcluir().get(avaliadorPage.getBotoesExcluir().size() - 1);
		botaoExcluirUltimoAvaliador.click();

		Alert alert = driver.switchTo().alert();
		assertEquals("Tem certeza que deseja excluir ?", alert.getText());
		alert.accept();

		FluentWait<WebDriver> wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(avaliadorPage.getMessagemGeral()));
		assertEquals("Avaliador excluído com sucesso", avaliadorPage.getMessagemGeral().getText());

		Integer numeroDeAvaliadoresFinal = getNumeroDeAvaliadores();
		assertEquals(numeroDeAvaliadoresInicial - 1, numeroDeAvaliadoresFinal, 0);
	}

	private int getNumeroDeAvaliadores() {
		return driver.findElements(By.xpath("id('lista')/table/tbody/tr")).size();
	}

	@After
	public void tearDown() throws Exception {
//		driver.quit();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	class ScreenshotTestRule implements MethodRule {
		public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
			return new Statement() {
				@Override
				public void evaluate() throws Throwable {
					try {
						statement.evaluate();
						driver.quit();
					} catch (Throwable t) {
						captureScreenshot(frameworkMethod.getName());
						driver.quit();
						throw t; // rethrow to allow the failure to be reported to JUnit
					}
				}

				public void captureScreenshot(String fileName) {
					try {
						new File("target/surefire-reports/").mkdirs(); // Insure directory is there
						FileOutputStream out = new FileOutputStream("target/surefire-reports/screenshot-" + fileName + ".png");
						out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
						out.close();
					} catch (Exception e) {
						// No need to crash the tests if the screenshot fails
					}
				}
			};
		}
	}

}
