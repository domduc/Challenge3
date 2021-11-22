package components;


import org.openqa.selenium.By;

public class TopMenuBar {

    //locator
    By Overview = By.xpath("//ul[@class='o_menu_sections']//span[text()='Overview']");

    //String xpath
    String Menu_Sections = "//ul[@class='o_menu_sections']//*[contains(@class,'o_menu') and contains(text(),'%s')]";

    public static void selectDropdownMenu(String strMenu, String strMenuItem) throws Exception{

    }

}
