package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class MertProject {
    WebDriver driver;
    @BeforeMethod
    public void setupMethod(){
         driver= WebDriverFactory.getDriver("chrome");
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.get("https://login2.nextbasecrm.com/");
        WebElement username = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        username.sendKeys("hr40@cydeo.com");
        WebElement password = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        password.sendKeys("UserUser");

        WebElement loginbutton = driver.findElement(By.xpath("//input[@type='submit']"));
        loginbutton.click();

        WebElement chatmodule=driver.findElement(By.xpath("//a[@title='Chat and Calls']"));
        chatmodule.click();

    }

@Test
    public void test(){
        WebElement message = driver.findElement(By.xpath("//div[@class='bx-desktop-tab-icon bx-desktop-tab-icon-im']"));
        String actualtext=message.getText();
    String expectedtext="Message";
    Assert.assertEquals(actualtext,expectedtext);
    }
    @Test
    public void test1(){

        WebElement Notification = driver.findElement(By.xpath("//div[@class='bx-desktop-tab-icon bx-desktop-tab-icon-notify']"));
        String actualtext=Notification.getText();
        String expectedtext="Notifications";

    }
    @Test
    public void test2(){
        WebElement Setting = driver.findElement(By.xpath("//div[@class='bx-desktop-tab-icon bx-desktop-tab-icon-config']"));
        String actualtext=Setting.getText();
        String expectedtext="Settings";
    }
    @Test
    public void test3(){
        WebElement activestream = driver.findElement(By.xpath("//div[@class='bx-desktop-tab-icon bx-desktop-tab-icon-im-lf']"));
        String actualtext=activestream.getText();
        String expectedtext="Active Stream";
        Assert.assertEquals(actualtext,expectedtext);
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