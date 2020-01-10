package com.braga.tests;

import com.braga.base.BaseTest;
import com.braga.utilities.CheckPoint;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @AfterMethod
    public void afterMethod() {
        if (pNavigation.isUserLoggedIn()) {
            pNavigation.logout();
            pNavigation.login();
        }
    }

    @Test
    public void testLogin() {
        pNavigation = pLogin.signInWith("test@email.com", "abcabc");
        CheckPoint.mark("01 - testLogin", pNavigation.verifyHeader(), "header verification");
        CheckPoint.markFinal("01 - testLogin", pNavigation.isUserLoggedIn(), "login verification");
        //Assert.assertTrue(pNavigation.isUserLoggedIn());
        //Assert.assertTrue(pNavigation.verifyHeader());
    }

    @Test
    public void testInvalidLogin() {
        pNavigation = pLogin.signInWith("test@email", "abcabc");
        Assert.assertFalse(pNavigation.isUserLoggedIn());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
