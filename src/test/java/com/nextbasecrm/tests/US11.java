package com.nextbasecrm.tests;


//1. The “My Profile” option should be displayed when the user clicks the user profile from the homepage.
//2. There should be five tabs on my profile page:
//       “General “Drive” “Tasks” “Calendar ” “conversations”
//Description
//As a user, I want to access my profile page.


import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class US11 {
    WebDriver driver;
    @BeforeMethod
    public void setupMethod() {
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver);
    }

    @Test
    public void click_user_profile() {
        //locate and click User profile
        WebElement userProfile = driver.findElement(By.xpath("//span[@id='user-name']"));
        userProfile.click();
        //locate My profile tab and click
        WebElement myProfile = driver.findElement(By.xpath("//span[.='My Profile']"));
        myProfile.click();
    }

    @Test
    public void my_profile() {


        String expected = " “General “Drive” “Tasks” “Calendar ” “conversations”";
        WebElement actual = driver.findElement(By.xpath("//div[@class='header-search header-search-empty']"));
        String actualresult = actual.getText();
        Assert.assertEquals(expected, actualresult);

    }




    @AfterMethod
    public void tear_down(){
        driver.close();
    }
}