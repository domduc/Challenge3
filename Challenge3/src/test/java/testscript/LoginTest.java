package testscript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pom.PageHome;
import pom.PageInventory;
import pom.PageLogin;
import pom.PageManufacturingOrders;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class LoginTest {

    @Test
    public void testLogin() throws Exception{

        SimpleDateFormat _date = new SimpleDateFormat("yyMMddhhmmssMs");
        String  aa = _date.format(new Date());
        String bbbl;
    }
}
