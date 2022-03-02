package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US6 {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver);
    }


    @Test
    public void more_Tab(){
        WebElement more_tab = driver.findElement(By.xpath("//span[@id='feed-add-post-form-link-text']"));
        more_tab.click();

        WebElement file = driver.findElement(By.xpath("//span[@class='menu-popup-item menu-popup-no-icon feed-add-post-form-file feed-add-post-form-file-more ']"));
        System.out.println("file.isDisplayed() = " + file.isDisplayed());


        WebElement appreciation = driver.findElement(By.xpath("//span[@class='menu-popup-item menu-popup-no-icon feed-add-post-form-grat feed-add-post-form-grat-more ']"));
        System.out.println("appreciation.isDisplayed() = " + appreciation.isDisplayed());

        WebElement announcement = driver.findElement(By.xpath("//span[@class='menu-popup-item menu-popup-no-icon feed-add-post-form-important feed-add-post-form-important-more ']"));
        System.out.println("announcement.isDisplayed() = " + announcement.isDisplayed());

        WebElement workflow = driver.findElement(By.xpath("//span[@class='menu-popup-item menu-popup-no-icon feed-add-post-form-lists feed-add-post-form-lists-more ']"));
        System.out.println("workflow.isDisplayed() = " + workflow.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }




}
//Title - As a user, I want to see all the options under the MORE tab in the homepage.
//Four options should be displayed under the MORE tab:
//File
//Appreciation
//Announcement
//Workflow
//Decription:
//As a user, I want to see all the options __ under the MORE tab in the homepage.