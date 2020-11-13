package com.braga.tests;

import com.braga.base.BaseTest;
import com.braga.base.DriverFactory;
import com.braga.utilities.CheckPoint;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Parallel extends BaseTest {
    private WebDriver driver;

    @BeforeMethod
    public void methodSetup() {
        CheckPoint.clearHashMap();
        driver = DriverFactory.getDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Finalizando: Thread: " + Thread.currentThread().getId());
        DriverFactory.quitDriver();
    }

    @Test
    public void test1() throws InterruptedException {
        driver.get(baseURL);
        System.out.println("test1: Thread: " + Thread.currentThread().getId());
        Thread.sleep(2000);
    }

    @Test
    public void test2() throws InterruptedException {
        driver.get(baseURL);
        System.out.println("test2: Thread: " + Thread.currentThread().getId());
        Thread.sleep(5000);
    }

    @Test
    public void test3() throws InterruptedException {
        driver.get(baseURL);
        System.out.println("test3: Thread: " + Thread.currentThread().getId());
        Thread.sleep(2000);
    }

    @Test
    public void test4() throws InterruptedException {
        driver.get(baseURL);
        System.out.println("test4: Thread: " + Thread.currentThread().getId());
        Thread.sleep(2000);
    }

    @Test
    public void test5() throws InterruptedException {
        driver.get(baseURL);
        System.out.println("test5: Thread: " + Thread.currentThread().getId());
        Thread.sleep(2000);
    }

    @Test
    public void test6() throws InterruptedException {
        driver.get(baseURL);
        System.out.println("test6: Thread: " + Thread.currentThread().getId());
        Thread.sleep(2000);
    }
}
