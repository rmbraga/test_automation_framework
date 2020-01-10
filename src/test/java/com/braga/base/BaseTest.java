package com.braga.base;

import com.braga.pages.*;
import com.braga.utilities.CheckPoint;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    public WebDriver driver;
    protected String baseURL;
    protected LoginPage pLogin;
    protected NavigationPage pNavigation;
    protected CategoryFilterPage pCategory;
    protected SearchBarPage pSearch;
    protected ResultsPage pResults;

    @BeforeClass
    @Parameters({"browser"})
    public void commonSetUp(String browser) {
        driver = DriverFactory.getInstance().getDriver(browser);
        baseURL = "https://learn.letskodeit.com";
        driver.get(baseURL);
        pNavigation = new NavigationPage(driver);
        pLogin = pNavigation.login();
    }

    @BeforeMethod
    public void methodSetup() {
        CheckPoint.clearHashMap();
    }

    @AfterClass
    public void commonTearDown() {
        DriverFactory.getInstance().quitDriver();
    }
}
