package com.unisinos.processoDeTeste.seleniumTeste;

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

import com.unisinos.processoDeTeste.seleniumTeste.pages.GooglePage;

@RunWith(BlockJUnit4ClassRunner.class)
public class ChromeTest extends TestCase {

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