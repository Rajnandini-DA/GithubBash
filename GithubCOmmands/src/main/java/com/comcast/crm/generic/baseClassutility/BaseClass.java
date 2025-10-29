package com.comcast.crm.generic.baseClassutility;


import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcost.crm.generic.databaseutility.DatabaseUtility;
import com.comcost.crm.generic.fileutility.ExcelUtility;
import com.comcost.crm.generic.fileutility.Fileutility;
import com.comcost.crm.generic.webdriverutility.Javautility;
import com.comcost.crm.generic.webdriverutility.UtilityClassObject;
import com.comcost.crm.generic.webdriverutility.WebDriverUtility;
import com.comcost.crm.objectrepositoryutility.HomePage;
import com.comcost.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	
	//Create object
	public DatabaseUtility dbutil = new DatabaseUtility();
	public Fileutility futil = new Fileutility();
	public ExcelUtility eutil = new ExcelUtility();
	public WebDriverUtility wutil = new WebDriverUtility();
	public Javautility jutil = new Javautility();
	public WebDriver driver ;
	public static WebDriver sdriver;
	
	
	@BeforeSuite(groups= {"smokeTest","regressionTest"})
	public void configBS() {
		System.out.println("==Connect to DB , Report Config==");
		dbutil.getDbConnection();
	}
	
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void configBC() throws IOException {
		System.out.println("==Launch the Browser==");
		String BROWSER = futil.getDatafromPropertiesFile("browser");
		if (BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		driver.get(futil.getDatafromPropertiesFile("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		UtilityClassObject.setDriver(driver);
	}
	
	
	@BeforeMethod(groups= {"smokeTest","regressionTest"})
	public void configBM() throws IOException {
		System.out.println("==Login to app==");
		String USERNAME = futil.getDatafromPropertiesFile("username");
		String PASSWORD = futil.getDatafromPropertiesFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.logintoApp(USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups= {"smokeTest","regressionTest"})
	public void ConfigAM() {
		System.out.println("==Logout from app==");
		HomePage hp = new HomePage(driver);
		hp.Logoutfromapp(driver);
	}
	
	@AfterClass(groups= {"smokeTest","regressionTest"})
	public void ConfigAC() {
		System.out.println("==Close the Browser==");
		driver.quit();
	}
	
	@AfterSuite(groups= {"smokeTest","regressionTest"})
	public void ConfigAS() {
		System.out.println("==Close the db==");
		dbutil.closeConnection();
	}
	
	
}
