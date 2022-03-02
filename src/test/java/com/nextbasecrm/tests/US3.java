package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US3 {

    WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver);

    }
    @AfterClass
    public void afterClass(){
        BrowserUtils.sleep(3);
        driver.close();
    }

    @Test
    public void logOut(){
        // Locate the Profile Button and click on it
        WebElement profileBtn = driver.findElement(By.xpath("//div[@id='user-block']"));
        profileBtn.click();

        // Locate the "Logout" Button
        WebElement logOutBtn = driver.findElement(By.xpath("//a[@href=\"/auth/?logout=yes&backurl=%2Fstream%2F\"]"));
        logOutBtn.click();

        // Checking the webpage Title to be the one that is expected Title
        BrowserUtils.verifyTitle(driver,"Authorization");
    }

}
