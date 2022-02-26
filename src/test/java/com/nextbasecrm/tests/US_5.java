package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US_5 {

    public WebDriver driver;

    @BeforeMethod
    public void setupMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test(){

        driver.get("https://login2.nextbasecrm.com/");

        CRM_Utilities.crm_login(driver, "helpdesk40@cybertekschool.com", "UserUser");


        String myMsg ="test";
        driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']")).sendKeys(myMsg);//

        //you mustr find send button locator and press on it

        //you musr assert that msg got posted

        //find element and use getText method and place in a string

        //Assert.assertEquals(myMsg.equalsIgnoreCase());
        //

        //Assert



    }

}
