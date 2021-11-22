package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageManufacturingOrders {

    //locator
    By btn_Create = By.xpath("//button[contains(text(), 'Create')]");
    By btn_Save = By.xpath("//button[contains(text(),'Save')]");
    By btn_Edit = By.xpath("//button[contains(text(),'Edit')]");
    By btn_Confirm = By.xpath("//button[@name = 'action_confirm']");
    By btn_Mark_As_Done = By.xpath("//button[child::span[text()='Mark as Done']]");
    By tfd_Producing_Quantity = By.xpath("//input[@name = 'qty_producing']");
    By tfd_Product_ID = By.xpath("//div[@name = 'product_id']//input");
    By tfd_Product_Quantity = By.xpath("//input[@name = 'product_qty']");
    By tfd_Product_UOM = By.xpath("//div[@name = 'product_uom_id']//input");
    By tab_Components = By.xpath("//div[@class= 'o_notebook' and descendant::a[text()='Components']]");
    By inputDropdown_Cell_Product_ID = By.xpath("//table[@style='table-layout: fixed;']//div[@name = 'product_id']//input");
    By tfd_Cell_Consume = By.xpath("//table[@style='table-layout: fixed;']//input[@name='product_uom_qty']");
    By inputDropdown_Cell_UOM = By.xpath("//table[@style='table-layout: fixed;']//div[@name = 'product_uom']//input");
    By btn_Add_Component = By.xpath("//table[@style='table-layout: fixed;']//a[text()='Add a line']");
    By lbl_MO_New = By.xpath("//li[@class = 'breadcrumb-item active' and text()='New']");

    WebDriver driver;
    WebDriverWait wait;

    public PageManufacturingOrders(WebDriver _driver, WebDriverWait _wait) {
        this.driver = _driver;
        this.wait = _wait;
    }

    public void createManufacturingOrder(String strConProdName, String strProduceProduct, int intConsumeAmount, int intProduceAmount, String strUnit) {
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_Create)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(lbl_MO_New));

        //select product
        selectMOProduct(tfd_Product_ID, "", strProduceProduct);

        //select quantity
        WebElement _e_Quantity = driver.findElement(tfd_Product_Quantity);
        _e_Quantity.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        _e_Quantity.sendKeys(String.valueOf(intProduceAmount));

        //add components
        driver.findElement(btn_Add_Component).click();

        //select product
        wait.until(ExpectedConditions.presenceOfElementLocated(inputDropdown_Cell_Product_ID));
        selectMOProduct(inputDropdown_Cell_Product_ID, "", strConProdName);

        //input the amount to consume
        WebElement _e_Component_Quantity = driver.findElement(tfd_Cell_Consume);
        _e_Component_Quantity.click();
        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }
        _e_Component_Quantity.sendKeys(String.valueOf(intConsumeAmount));

        //select UOM
        selectInputDropdown(inputDropdown_Cell_UOM, "", strUnit);

        //Save the MO
        driver.findElement(btn_Save).click();

        //click to confirm
        wait.until(ExpectedConditions.elementToBeClickable(btn_Edit));
        driver.findElement(btn_Confirm).click();

        //click to edit button
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_Mark_As_Done));
        wait.until(ExpectedConditions.elementToBeClickable(btn_Edit)).click();

        //update producing quantity
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_Save));
        WebElement _eProducing_Quantity = driver.findElement(tfd_Producing_Quantity);
        _eProducing_Quantity.click();
        _eProducing_Quantity.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        _eProducing_Quantity.sendKeys(String.valueOf(intProduceAmount));
        driver.findElement(btn_Save).click();

        //waiting for saving
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_Edit));
        wait.until(ExpectedConditions.elementToBeClickable(btn_Mark_As_Done)).click();
    }

    /**
     * Select the popup value after clicking on the locator (input text)
     *
     * @param by_InputLocator
     * @param strOriginValue
     * @param strExpectedValue
     */
    public void selectInputDropdown(By by_InputLocator, String strOriginValue, String strExpectedValue) {

        String xpath_Menu_Item = "//ul[@id and not(contains(@style, 'display: none;'))]/li/a[text()='%s']";
        // Locator
        By _menu_Popup = By.xpath("//ul[@id and not(contains(@style, 'display: none;'))]");
        By _menu_Item = By.xpath(String.format(xpath_Menu_Item, strExpectedValue));

        //clear the text
        WebElement _eInput = driver.findElement(by_InputLocator);
        _eInput.click();
        _eInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        _eInput.sendKeys(strExpectedValue);

        //verify the popup displayed
        wait.until(ExpectedConditions.presenceOfElementLocated(_menu_Popup));

        // select the item on popup
        wait.until(ExpectedConditions.presenceOfElementLocated(_menu_Item)).click();

        //div[@role = 'status']/div[text()='cm'][last()]
        //ul[descendant::a[text()='cm']]/li[child::a[text()='cm']]
    }
    public void selectMOProduct(By by_InputLocator, String strOriginValue, String strExpectedValue) {

        String xpath_Menu_Item = "//ul[@id and not(contains(@style, 'display: none;'))]/li/a[contains(text(),'%s')]";
        // Locator
        By _menu_Popup = By.xpath("//ul[@id and not(contains(@style, 'display: none;'))]");
        By _menu_Item = By.xpath(String.format(xpath_Menu_Item, strExpectedValue));

        //clear the text
        WebElement _eInput = driver.findElement(by_InputLocator);
        _eInput.click();
        _eInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        _eInput.sendKeys(strExpectedValue);

        //verify the popup displayed
        wait.until(ExpectedConditions.presenceOfElementLocated(_menu_Popup));

        // select the item on popup
        wait.until(ExpectedConditions.presenceOfElementLocated(_menu_Item)).click();

        //div[@role = 'status']/div[text()='cm'][last()]
        //ul[descendant::a[text()='cm']]/li[child::a[text()='cm']]
    }
}
