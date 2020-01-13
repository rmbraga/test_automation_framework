package com.braga.base;

import com.braga.utilities.Constants;
import com.braga.utilities.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final Logger log = LogManager.getLogger(DriverFactory.class.getName());
    // Singleton
    // Only one instance of the class can exist at a time
    private static final DriverFactory instance = new DriverFactory();

    private DriverFactory() {
    }

    public static DriverFactory getInstance() {
        return instance;
    }

    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();

    public WebDriver getDriver(String browser) {
        WebDriver driver = null;
        setDriver(browser);
        if (threadedDriver.get() == null) {
            try {
                if (browser.equalsIgnoreCase(Constants.FF)) {
                    driver = new FirefoxDriver(setFFOptions());
                    threadedDriver.set(driver);
                } else if (browser.equalsIgnoreCase(Constants.CHROME)) {
                    driver = new ChromeDriver(setChromeOptions());
                    threadedDriver.set(driver);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            threadedDriver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            threadedDriver.get().manage().window().maximize();
        }
        return threadedDriver.get();
    }

    public void quitDriver() {
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }

    private void setDriver(String browser) {
        String driverPath = "";
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        String directory = Constants.USER_DIRECTORY + "//" + Constants.DRIVERS_DIRECTORY;
        String driverKey = "";
        String driverValue = "";

        log.info("OS name from system property :: " + os);

        if (browser.equalsIgnoreCase(Constants.FF)) {
            driverKey = Constants.GECKO_DRIVER_KEY;
            driverValue = Constants.GECKO_DRIVER_VALUE;
        } else if (browser.equalsIgnoreCase(Constants.CHROME)) {
            driverKey = Constants.CHROME_DRIVER_KEY;
            driverValue = Constants.CHROME_DRIVER_VALUE;
        } else {
            log.info("Browser type not implemented/supported!");
        }

        driverPath = directory + driverValue + (os.equals("win") ? ".exe" : "");
        log.info("Driver Binary :: " + driverPath);
        System.setProperty(driverKey, driverPath);
    }

    private ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-infobars");
        return options;
    }

    private FirefoxOptions setFFOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        return options;
    }
}
