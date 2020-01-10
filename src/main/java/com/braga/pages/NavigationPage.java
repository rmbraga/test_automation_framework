package com.braga.pages;

import com.braga.base.BasePage;
import com.braga.utilities.Util;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NavigationPage extends BasePage {

    public NavigationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    /***
     * Variables
     * URL
     * Title
     */
    public WebDriver driver;
    private JavascriptExecutor js;
    private final String URL = "https://learn.letskodeit.com/courses";
    private String ALL_COURSES_LINK = "xpath=>//a[contains(text(), 'All Courses')]";
    private String MY_COURSES_LINK = "xpath=>//a[contains(text(), 'My Courses')]";
    private String ACCOUNT_ICON = "class=>gravatar";
    private String LOGIN_LINK = "xpath=>//a[@href='/sign_in']";
    private String LOGOUT_LINK = "xpath=>//a[@href='/sign_out']";

    /***
     * Methods
     */

    public void allCourses() {
        elementClick(ALL_COURSES_LINK, "ALL Courses Link");
    }

    public void myCourses() {
        elementClick(MY_COURSES_LINK, "My Courses Link");
    }

    public boolean isUserLoggedIn() {
        try {
            List<WebElement> accountImage = getElementList(ACCOUNT_ICON, "Account Icon");
            if (accountImage.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public LoginPage login() {
        elementClick(LOGIN_LINK, "Login Link");
        return new LoginPage(driver);
    }

    public void logout() {
        elementClick(ACCOUNT_ICON, "User Account Icon");
        WebElement logoutLink = waitForElement(LOGOUT_LINK, 10);
        javascriptClick(logoutLink, "Logout Link");
    }

    public boolean verifyHeader() {
        WebElement link = getElement(ALL_COURSES_LINK, "All Courses Link");
        return Util.verifyTextContains(link.getText(), "All Coursesesss");
    }
}

