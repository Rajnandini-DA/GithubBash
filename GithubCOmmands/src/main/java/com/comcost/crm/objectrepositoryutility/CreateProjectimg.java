package com.comcost.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProjectimg {

	
	WebDriver driver;

	public CreateProjectimg(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@title='Create Projects...']")
	private WebElement projectlookupimage;

	public WebElement getprojectlookupimage() {
		return projectlookupimage;
	}

	
	
}
