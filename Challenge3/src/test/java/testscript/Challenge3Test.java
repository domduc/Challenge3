package testscript;

import helper.GenerateUnique;
import helper.JsonHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import pom.PageHome;
import pom.PageInventory;
import pom.PageManufacturingOrders;

import java.text.SimpleDateFormat;

public class Challenge3Test extends TestScript{

    @Test
    public void testCreateMO() throws Exception{

        //initial
        PageInventory _pInventory = new PageInventory(driver,wait);
        PageInventory.PageInventoryProduct _pProduct = _pInventory.new PageInventoryProduct(driver,wait);
        PageManufacturingOrders _pMO = new PageManufacturingOrders(driver,wait);

        //get test data
        JSONObject _jObject = JsonHelper.parsetoJson("Challenge3Test.json");
        String strUsername = (String) _jObject.get("username");
        String strPassword = (String) _jObject.get("password");
        JSONObject comsumeProduct = (JSONObject) _jObject.get("comsumeproduct");
        JSONObject produceProduct = (JSONObject) _jObject.get("produceproduct");
        JSONObject manufacturingOrders = (JSONObject) _jObject.get("manufacturingorders");

        String _uniqueText = GenerateUnique.getUniqueDate();
        String strComsumeProductName = ((String) comsumeProduct.get("productname")).concat(_uniqueText);
        comsumeProduct.put("productname", strComsumeProductName);

        String strProduceProductName = ((String) produceProduct.get("productname")).concat(_uniqueText);
        produceProduct.put("productname", strProduceProductName);


        log.info("Login to Odoo");
        pLogin.Login(strUsername, strPassword);

        log.info("Navigate to Inventory page");
        wait.until(ExpectedConditions.presenceOfElementLocated(PageHome.icon_User));
        pHome.navigateHome2Item(PageHome.HomeMenuItem.Inventory);

        log.info("Open the Products page");
        _pInventory.selectMenuHeaderItem("Products", "Products");

        log.info("Create consume product");
        _pProduct.createProduct(comsumeProduct);
        String _refConsumeName =_pProduct.getRefProductName(strComsumeProductName);

        log.info("Update consume product quantity");
        _pProduct.updateQuantity("WH/Stock","1",20000);

        log.info("Open the Products page");
        _pInventory.selectMenuHeaderItem("Products", "Products");

        log.info("Create produce product");
        _pProduct.createProduct(produceProduct);
        String _refProduceName =_pProduct.getRefProductName(strProduceProductName);

        log.info("Go to Home page");
        pHome.goToHome();

        log.info("Open the Products page");
        pHome.navigateHome2Item(PageHome.HomeMenuItem.Manufacturing);

        log.info("Create MO of product");
        _pMO.createManufacturingOrder(strComsumeProductName, strProduceProductName,20000,20000,"Units");

    }
}
