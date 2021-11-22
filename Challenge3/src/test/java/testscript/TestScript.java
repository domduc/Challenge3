package testscript;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pom.PageHome;
import pom.PageLogin;
import driver.DriverFactory;

import java.time.Duration;

public class TestScript {

    protected String strURL = System.getProperty("url");
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected PageLogin pLogin;
    protected PageHome pHome;
    protected Logger log;

    @BeforeMethod
    public void initialDriver() {
        DriverFactory.Builder build = new DriverFactory.Builder();
        build.setStrBrowserType(System.getProperty("browser"));
        build.setStrPlatform(System.getProperty("platform"));
        build.setMaximized(true);
        build.buildCapabilities();
        DriverFactory driverFactory = build.build();

        driver = driverFactory.getWebDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        pLogin = new PageLogin(driver, wait);
        pHome = new PageHome(driver, wait);
        log = org.apache.log4j.Logger.getLogger("testscript");

        //navigate to Odoo
        driver.get(strURL);
    }

    @AfterMethod
    public void PostExecution(){
//        driver.quit();
    }

}
