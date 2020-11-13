package com.braga.base;

import com.braga.pages.*;
import com.braga.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
    private WebDriver driver;
    protected String baseURL;
    protected LoginPage pLogin;
    protected NavigationPage pNavigation;
    protected CategoryFilterPage pCategory;
    protected SearchBarPage pSearch;
    protected ResultsPage pResults;
    protected String browser;

    @BeforeClass
    @Parameters({"browser"})
    public void commonSetUp(String browser) {
        this.browser = browser;
        baseURL = Constants.BASE_URL;
    }

//    @AfterClass
//    public void commonTearDown() {
//        DriverFactory.quitDriver();
//    }
}
