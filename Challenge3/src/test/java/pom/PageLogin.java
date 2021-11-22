package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageLogin {

    private WebDriver driver;
    private WebDriverWait wait;

    //initial locator
    By txt_Email = By.xpath("//input[@id = 'login']");
    By txt_Password = By.xpath("//input[@id = 'password']");
    By btn_Login = By.xpath("//button[text()='Log in']");
    By btn_Reset_Password = By.xpath("//div[child::*[text()='Reset Password']]");
    By img_Logo =  By.xpath("//img[@alt = 'Logo']");


    /**
     * Constructor of Login Page
     * @param _driver
     * @param _wait
     */
    public PageLogin(WebDriver _driver, WebDriverWait _wait){
        this.driver = _driver;
        this.wait = _wait;
    }

    /**
     * Need to standing on the login page to use the function
     *
     * @param strUsername
     * @param strPassword
     * @throws Exception
     */
    public void Login(String strUsername, String strPassword) throws Exception{
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_Login));
        driver.findElement(txt_Email).sendKeys(strUsername);
        driver.findElement(txt_Password).sendKeys(strPassword);
        driver.findElement(btn_Login).click();
    }
}
