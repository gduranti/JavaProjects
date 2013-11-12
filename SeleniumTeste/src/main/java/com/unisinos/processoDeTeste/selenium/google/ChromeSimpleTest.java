package com.unisinos.processoDeTeste.selenium.google;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.unisinos.processoDeTeste.selenium.google.pages.GooglePage;

@RunWith(BlockJUnit4ClassRunner.class)
public class ChromeSimpleTest extends TestCase {

	private static ChromeDriverService service;
	private WebDriver driver;

	@BeforeClass
	public static void createAndStartService() throws IOException {
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("src/main/resources/web-drivers/chromedriver.exe"))
				.usingAnyFreePort().build();
		service.start();
	}

	@AfterClass
	public static void createAndStopService() {
		service.stop();
	}

	@Before
	public void createDriver() {
		driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
	}

	@After
	public void quitDriver() {
		driver.quit();
	}
	
	@Test
	public void testSimples1() {
		
		driver.get("C:\\Users\\Usuario\\Desktop\\teste.html");
		
		WebElement nome = driver.findElement(By.id("nome"));
		nome.sendKeys("Gabriel");
		
		WebElement idade = driver.findElement(By.id("idade"));
		idade.sendKeys("21");
		
		WebElement endereco = driver.findElement(By.id("endereco"));
		endereco.sendKeys("Rua xis, numero 2");
		
		WebElement telefone = driver.findElement(By.id("telefone"));
		telefone.sendKeys("51 8888 7777");
		
		WebElement btnSalvar = driver.findElement(By.id("btnSalvar"));
		btnSalvar.click();
		
		FluentWait<WebDriver> wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
		assertEquals("Bem vindo", alert.getText());
	}

	@Test
	public void testGoogleSearch() {
		
		driver.get("http://www.google.com");
		
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("webdriver");
		
		WebElement searchButton = driver.findElement(By.name("btnG"));
		searchButton.click();
		
		FluentWait<WebDriver> wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleContains("webdriver"));
        
		assertEquals("webdriver - Pesquisa Google", driver.getTitle());
	}
	
	@Test
	public void testGoogleSearch2() {
		
		driver.get("http://www.google.com");
		
		GooglePage googlePage = PageFactory.initElements(driver, GooglePage.class);
		
		googlePage.setSearchBoxKeys("Gabriel");
		googlePage.clickSearchButton();
		
		FluentWait<WebDriver> wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(googlePage.getSearchResult()));
		
		assertEquals("Gabriel - Pesquisa Google", driver.getTitle());
	}
	
}