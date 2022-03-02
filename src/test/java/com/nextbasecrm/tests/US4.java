package com.nextbasecrm.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class US4 {

    WebDriver driver;

    @BeforeMethod
    public void setupMethod(){
        driver = com.nextbasecrm.utilities.WebDriverFactory.getDriver(com.nextbasecrm.utilities.ConfigurationReader.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(com.nextbasecrm.utilities.ConfigurationReader.getProperty("env"));
        com.nextbasecrm.utilities.CRM_Utilities.crm_login(driver, com.nextbasecrm.utilities.ConfigurationReader.getProperty("username"),"UserUser");
    }

    @Test
    public void user_see_5_options_user_profile_dropdown(){
        //Sending valid password to password input and hit the enter

        WebElement userProfile = driver.findElement(By.xpath("//*[@id=\"user-block\"]"));
        userProfile.click();

        List<String> expectedOptions = new ArrayList<>(Arrays.asList("My Profile","Edit Profile Settings","Themes", "Configure notifications", "Log out"));
        List<String > actualOptions = new ArrayList<>();
        com.nextbasecrm.utilities.BrowserUtils.sleep(2);

        List<WebElement> userDropdownOptions = driver.findElements(By.xpath("//*[@class=\"menu-popup-item-text\"]"));
        for (WebElement eachOption : userDropdownOptions) {
            actualOptions.add(eachOption.getText());
        }
        System.out.println(actualOptions);
        Assert.assertEquals(actualOptions,expectedOptions,"User cannot see correct profile options");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

