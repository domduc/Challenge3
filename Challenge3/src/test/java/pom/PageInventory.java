package pom;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageInventory {
    WebDriver driver = null;
    WebDriverWait wait = null;

    //locator
    String xpaht_Menu_Header = "//a[contains(@class,'o_menu_header') and contains(text(),'%s')]";
    String xpath_Menu_Item = "//a[@role = 'menuitem']//span[text()='%s']";
    By Overview_Menu = By.xpath("//ul[@class = 'o_menu_sections']//span[text()='Overview']");

    public PageInventory(WebDriver _driver, WebDriverWait _wait) {
        this.driver = _driver;
        this.wait = _wait;
    }

    /**
     * select the menu header on the inventory page
     *
     * @param strMenuName
     * @throws Exception
     */
    public void selectMenuHeader(String strMenuName) throws Exception {
        By Menu_Header = By.xpath(String.format(xpaht_Menu_Header, strMenuName));
        //waiting for the Menu Header loading completed
        wait.until(ExpectedConditions.presenceOfElementLocated(Overview_Menu));

        if (strMenuName.equals("Overview")) {
            driver.findElement(Overview_Menu).click();
        } else {
            driver.findElement(Menu_Header).click();
        }
    }

    /**
     * Select the menu item after select the menu header
     *
     * @param strMenuName
     * @param strMenuItem
     * @throws Exception
     */
    public void selectMenuHeaderItem(String strMenuName, String strMenuItem) throws Exception {
        By Menu_Item = By.xpath(String.format(xpath_Menu_Item, strMenuItem));
        if (strMenuName.equals("Overview")) {
            selectMenuHeader(strMenuName);
        } else {
            selectMenuHeader(strMenuName);
            wait.until(ExpectedConditions.presenceOfElementLocated(Menu_Item));
            driver.findElement(Menu_Item).click();
        }
    }

    public class PageInventoryProduct {
        WebDriver driver;
        WebDriverWait wait;

        //locator
        // product home page
        By btn_Create = By.xpath("//button[contains(text(), 'Create')]");
        By btn_Filter = By.xpath("//button[child::span[text()='Filters']]");
        By btn_Group_By = By.xpath("//button[child::span[text()='Group By']]");
        By tfd_Search_Product = By.xpath("//div[@class = 'o_searchview_input_container']//input");
        String lbl_Product_Name_Kanban = "//strong[@class='o_kanban_record_title']/span[contains(text(),'%s')]";

        // Product creation form
        By btn_Save = By.xpath("//button[contains(text(),'Save')]");
        By btn_Discard = By.xpath("//button[contains(text(),'Discard')]");
        By tfd_Prodcut_Name = By.xpath("//input[@name ='name']");
        By ckb_Can_Be_Sold = By.xpath("//div[@name ='sale_ok']/input[@type = 'checkbox']");
        By ckb_Can_Be_Purchased = By.xpath("//div[@name ='purchase_ok']/input[@type = 'checkbox']");
        By btn_Edit = By.xpath("//button[contains(text(), 'Edit')]");

        // Product Creation > General Information
        By sel_Product_Type = By.xpath("//select[@name = 'type']");
        By inputDropdown_Product_Category = By.xpath("//div[@name = 'categ_id']//input");
        By tfd_Internal_Ref = By.xpath("//input[@name='default_code']");
        By tfd_Sales_Price = By.xpath("//div[@name = 'list_price']//input");
        By tfd_Cost = By.xpath("//div[@name = 'standard_price']/input");
        By btn_Update_Quantity = By.xpath("//button[child::span[text()='Update Quantity']]");
        By lbl_On_Hand_Quantity = By.xpath("//div[@name = 'qty_available']//span[@class='o_stat_value']");
        By lbl_Forecasted_Quantity = By.xpath("//div[@name = 'virtual_available']//span[@class='o_stat_value']");

        //Update quantity page
        By lbl_Update_Quantity_Title = By.xpath("//li[@class = 'breadcrumb-item active' and text()='Update Quantity']");
        By lbl_Product_Name = By.xpath("//li[@class = 'breadcrumb-item active']");
        By tbl_Quantity = By.xpath("//table");
        By inputDropDown_Package = By.xpath("//table//div[@name = 'package_id']//input");
        By inputDropDown_Location = By.xpath("//table//div[@name = 'location_id']//input");
        By tfd_On_Hand_Quantity = By.xpath("//table//input[@name = 'inventory_quantity']");

        public PageInventoryProduct(WebDriver _driver, WebDriverWait _wait) {
            this.driver = _driver;
            this.wait = _wait;
        }

        public void createProduct(String strProductName) {
            // waiting for product page loading completed and click on create button
            wait.until(ExpectedConditions.presenceOfElementLocated(btn_Create));
            driver.findElement(btn_Create).click();

            // waiting for the creation page
            wait.until(ExpectedConditions.presenceOfElementLocated(tfd_Prodcut_Name));
            driver.findElement(tfd_Prodcut_Name).sendKeys(strProductName);

            //General information tab
            // product type
            Select _slt = new Select(driver.findElement(sel_Product_Type));
            _slt.selectByVisibleText("Storable Product");

            // product category
            selectInputDropdown(inputDropdown_Product_Category, "All", "All / Saleable");

            // internal reference
            driver.findElement(tfd_Internal_Ref).sendKeys("Storable001");
            // barcode
            // Sales Price

            WebElement _e_Sale_Price = driver.findElement(tfd_Sales_Price);
            _e_Sale_Price.click();
            _e_Sale_Price.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            _e_Sale_Price.sendKeys("135");

            // Cost
            driver.findElement(tfd_Cost).clear();
            WebElement _e_Cost = driver.findElement(tfd_Cost);
            _e_Cost.click();
            _e_Cost.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            _e_Cost.sendKeys("0.1");
            // Unit of measure
            // Purchase unit of measure
            // Internal note


            //Inventory tab
            // Operation
            // Logistics
            // Traceability
            // Packaging
            // Description for Delivery Orders
            // Description for Receipts
            // Description for Internal Transfers

            driver.findElement(btn_Save).click();

        }

        public void createProduct(JSONObject jsonProduct) {

            //get data on object
            String strProductName = (String) jsonProduct.get("productname");
            String strProductType = (String) jsonProduct.get("producttype");
            String strProductCate = (String) jsonProduct.get("productcategory");
            String strInternalReference = (String) jsonProduct.get("internalreference");
            String strSalePrice = (String) jsonProduct.get("salesprice");
            String strCost = (String) jsonProduct.get("cost");
            String strUOM = (String) jsonProduct.get("uom");
            String strPurUOM = (String) jsonProduct.get("purchaseuom");


            // waiting for product page loading completed and click on create button
            wait.until(ExpectedConditions.elementToBeClickable(btn_Create)).click();

            // waiting for the creation page
            wait.until(ExpectedConditions.presenceOfElementLocated(tfd_Prodcut_Name));
            driver.findElement(tfd_Prodcut_Name).sendKeys(strProductName);

            //General information tab
            // product type
            Select _slt = new Select(driver.findElement(sel_Product_Type));
            _slt.selectByVisibleText(strProductType);

            // product category
            selectInputDropdown(inputDropdown_Product_Category, "All", strProductCate);

            // internal reference
            driver.findElement(tfd_Internal_Ref).sendKeys(strInternalReference);
            // barcode
            // Sales Price

            WebElement _e_Sale_Price = driver.findElement(tfd_Sales_Price);
            _e_Sale_Price.click();
            _e_Sale_Price.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            _e_Sale_Price.sendKeys(strSalePrice);

            // Cost
            driver.findElement(tfd_Cost).clear();
            WebElement _e_Cost = driver.findElement(tfd_Cost);
            _e_Cost.click();
            _e_Cost.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            _e_Cost.sendKeys(strCost);
            // Unit of measure
            // Purchase unit of measure
            // Internal note

            //Inventory tab
            // Operation
            // Logistics
            // Traceability
            // Packaging
            // Description for Delivery Orders
            // Description for Receipts
            // Description for Internal Transfers

            driver.findElement(btn_Save).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(btn_Edit));

        }

        public String getRefProductName(String _strProductName) {
            String xpath = "//li[@class = 'breadcrumb-item active' and contains(text(),'%s')]";
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(xpath, _strProductName))));
            String _prodName = driver.findElement(lbl_Product_Name).getText();
            return _prodName;
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

        /**
         * Add quantity on the update quantity page
         *
         * @param strLocation
         * @param strPackge
         * @param intQuantity
         */
        public void addQuantity(String strLocation, String strPackge, Integer intQuantity) {
            //select Location
            wait.until(ExpectedConditions.elementToBeClickable(inputDropDown_Location));
            selectInputDropdown(inputDropDown_Location, "", strLocation);
            // select Package
            selectInputDropdown(inputDropDown_Package, "", strPackge);
            // select On Hand Quantity
            WebElement _e_Quantity = driver.findElement(tfd_On_Hand_Quantity);
            _e_Quantity.click();
            _e_Quantity.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            _e_Quantity.sendKeys(String.valueOf(intQuantity));
            //
        }

        /**
         * Update quantity of a product
         *
         * @param strLocation
         * @param strPackge
         * @param intQuantity
         */
        public void updateQuantity(String strLocation, String strPackge, Integer intQuantity) {
            //Update Quantity
            wait.until(ExpectedConditions.presenceOfElementLocated(btn_Edit));
            wait.until(ExpectedConditions.elementToBeClickable(btn_Update_Quantity)).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(lbl_Update_Quantity_Title)).click();

            driver.findElement(btn_Create).click();
            addQuantity(strLocation, strPackge, intQuantity);
            driver.findElement(btn_Save).click();
        }

        /**
         * Searching by Product Name
         *
         * @param strProdName
         * @param strSearchBy
         */
        public void searchProduct(String strProdName, String strSearchBy) throws Exception {
            try {
                String _strSearchBy = String.format("//li[descendant::em[text()='%s']]", strSearchBy);
                wait.until(ExpectedConditions.presenceOfElementLocated(tfd_Search_Product)).click();
                Thread.sleep(500);
                driver.findElement(tfd_Search_Product).sendKeys(strProdName);
                Thread.sleep(1000);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(_strSearchBy))).click();
            } catch (Exception e) {
                throw e;
            }
        }

        /**
         * Click the product on kanban board
         *
         * @param strProdName
         */
        public void openProduct(String strProdName) {
            By _xpath = By.xpath(String.format(lbl_Product_Name_Kanban, strProdName));
            wait.until(ExpectedConditions.presenceOfElementLocated(_xpath)).click();
        }

        /**
         * get the on hand of a product
         *
         * @return
         */
        public String getOnHandAmount() {
            return wait.until(ExpectedConditions.presenceOfElementLocated(lbl_On_Hand_Quantity)).getText();
        }
    }
}
