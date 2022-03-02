package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.module.Configuration;

public class US2 {

    WebDriver driver;

    @BeforeMethod

    public void setup(){
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("env"));
    }

    @Test

    public void checkBox_Label_Displayed(){
        // I located "Remember me checkBox
        WebElement remember_Me_checkBox = driver.findElement(By.xpath("//input[@id='USER_REMEMBER']"));

        // I verified if it is displayed on the page
        Assert.assertTrue(remember_Me_checkBox.isDisplayed());
    }

    @Test

    public void remember_Me_on_This_Computer_message(){
        // On the loGin page I located "Remember me on this computer"
        WebElement remember_Me_on_This_Computer_message = driver.findElement(By.className("login-item-checkbox-label"));
        String expectedText = "Remember me on this computer";
        String actualText = remember_Me_on_This_Computer_message.getText();
        // I compared if actual and expected results are matched
        Assert.assertEquals(actualText,expectedText,"Actual result text is NOT as expected one");
    }

    @AfterMethod

    public void tearDown(){

        driver.close();
    }










}

/*
user story:

As a user, I should be able to save my user credential on a computer.

Acceptance Criteria:

1. There should be a Checkbox label displayed to enable users to save their user credentials on computers.
2. “Remember me on this computer” should be displayed on the left side of the checkbox label.


 */