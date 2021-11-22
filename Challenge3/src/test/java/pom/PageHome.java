package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageHome {

    //initial parameter
    WebDriver driver = null;
    WebDriverWait wait = null;

    //initial locator
    public static By icon_Messages = By.xpath("//nav[@class= 'o_main_navbar']//i[@aria-label='Messages']");
    public static By icon_User = By.xpath("//nav[@class= 'o_main_navbar']//img[@alt='User']");
    public static By lb_Username = By.xpath("//li[@class='o_user_menu']//span[@class='oe_topbar_name']");
    public static By icon_Application = By.xpath("//a[@title = 'Applications']");

    String strHomeMenuItem = "//a[@role='option' and child::*[text()='%s']]";

    public PageHome(WebDriver _driver, WebDriverWait _wait) {
        this.driver = _driver;
        this.wait = _wait;
    }

    /**
     * Select the menu item on the home page
     * @param eNumItem
     * @throws Exception
     */
    public void navigateHome2Item(HomeMenuItem eNumItem) throws Exception{
        By option_Home_Menu =  By.xpath(String.format(strHomeMenuItem, eNumItem));
        wait.until(ExpectedConditions.presenceOfElementLocated(option_Home_Menu)).click();
    }

    /**
     * Go to the Homer page
     */
    public void goToHome(){
        wait.until(ExpectedConditions.presenceOfElementLocated(icon_Application)).click();
    }

    public enum HomeMenuItem{
        Discuss,
        Inventory,
        Manufacturing,
        Barcode
    }
}
