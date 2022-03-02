package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US12 {

    WebDriver driver;

    @BeforeClass
    public void setUP(){
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));

    }

    @BeforeMethod
    public void loginToPage(){
        WebElement userName = driver.findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys(ConfigurationReader.getProperty("username"));

        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
        password.sendKeys(ConfigurationReader.getProperty("password") + Keys.ENTER);

    }

    @Test
    public void make_Announcements(){
        WebElement more_tab = driver.findElement(By.xpath("//span[@id='feed-add-post-form-link-text']"));
        more_tab.click();

        WebElement announcementDropdown = driver.findElement(By.xpath("//span[@class='menu-popup-item menu-popup-no-icon feed-add-post-form-important feed-add-post-form-important-more ']"));
        announcementDropdown.click();


        driver.switchTo().frame(0);
        WebElement announcement_box = driver.findElement(By.xpath("//body[@contenteditable='true']"));

        announcement_box.sendKeys("Today we will have a productive day.");


        driver.switchTo().defaultContent();
        WebElement clickSend = driver.findElement(By.xpath("//button[@class='ui-btn ui-btn-lg ui-btn-primary']"));
        clickSend.click();

    }

    @Test
    public void noMessageAssertion(){

        WebElement more_tab = driver.findElement(By.xpath("//span[@id='feed-add-post-form-link-text']"));
        more_tab.click();

        WebElement announcementDropdown = driver.findElement(By.xpath("//span[@class='menu-popup-item menu-popup-no-icon feed-add-post-form-important feed-add-post-form-important-more ']"));
        announcementDropdown.click();


        driver.switchTo().frame(0);
        WebElement announcement_box = driver.findElement(By.xpath("//body[@contenteditable='true']"));

        announcement_box.click();

        driver.switchTo().defaultContent();
        WebElement clickSend = driver.findElement(By.xpath("//button[@class='ui-btn ui-btn-lg ui-btn-primary']"));
        clickSend.click();

        String actual = driver.findElement(By.xpath("//span[@class='feed-add-info-text']")).getText();
        String expected = "The message title is not specified";

        Assert.assertEquals(actual, expected);

        WebElement act = driver.findElement(By.xpath("//span[@class='feed-add-info-text']"));
        boolean finalStatement = act.isDisplayed();
        if(finalStatement == true){
            System.out.println("The error message is displayed!");
        }else{
            System.out.println("The error message is NOT displayed!");
        }
    }
}



/*
    User story:
    As a user, I should be able to Make Announcements using the Announcements tab.
    Acceptance Criteria:
    1. Users should be able to write messages in and send announcements by clicking the SEND button.
    2. When users attempt to make announcements without a message, there should be a working message
    “The message title is not specified”.

     */