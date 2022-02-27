package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class US8 {
    WebDriver driver;
    @BeforeMethod
    public void setupMethod(){
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver);

        WebElement chatmodule=driver.findElement(By.xpath("//a[@title='Chat and Calls']"));
        chatmodule.click();

    }

    @Test
    public void test(){
        WebElement message = driver.findElement(By.xpath("//div[@id='bx-desktop-tab-im']"));
        String actualtext=message.getAttribute("title");

        String expectedtext="Message";
        Assert.assertTrue(actualtext.contains(expectedtext));
    }
    @Test
    public void test1(){

        WebElement Notification = driver.findElement(By.xpath("//div[@id='bx-desktop-tab-notify']"));
        String actualtext=Notification.getAttribute("title");
        String expectedtext="Notifications";
        Assert.assertTrue(actualtext.contains(expectedtext));
    }
    @Test
    public void test2(){
        WebElement Setting = driver.findElement(By.xpath("//div[@id='bx-desktop-tab-config']"));
        String actualtext=Setting.getAttribute("title");
        String expectedtext="Settings";
        Assert.assertTrue(actualtext.contains(expectedtext));
    }
    @Test
    public void test3(){
        WebElement activestream = driver.findElement(By.xpath("//div[@id='bx-desktop-tab-im-lf']"));
        String actualtext=activestream.getAttribute("title");
        String expectedtext="Active Stream";
        Assert.assertTrue(actualtext.contains(expectedtext));
        ;
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }



}
/*

1. There should be four sub-modules once the user clicks the Chat and Calls module:
Message
Notifications
Settings
Active Stream
 */