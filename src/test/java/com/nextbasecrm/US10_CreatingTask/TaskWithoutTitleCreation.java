package com.nextbasecrm.US10_CreatingTask;

import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TaskWithoutTitleCreation {

    public WebDriver driver;
    @BeforeMethod
    public  void driverSetup(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void taskWithoutTitle_creation_testHR()   {

        driver.get("https://login2.nextbasecrm.com/");
//As an HR user go to  the main page of the website
        CRM_Utilities.crm_login(driver, "hr40@cydeo.com", "UserUser");
//I'm able to see the dashboard
        WebElement taskTab = driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']"));
        Assert.assertTrue(taskTab.isDisplayed());

//Click to the 'TASK' tab in the upper part of the webpage
        taskTab.click();
//without locating the task form, test won't pass. Implicit wait won't help
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id=\"bx-html-editor-iframe-cnt-lifefeed_task_form\"]/iframe")));
        WebElement taskBody= driver.findElement(By.xpath("//html/body[@style='min-height: 84px;']"));
        driver.switchTo().parentFrame();

//Click 'SEND' button
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();


//Verify “The task name is not specified.” massage is displayed.
        WebElement message =driver.findElement(By.xpath("//div[.='The task name is not specified.']"));
        Assert.assertTrue(message.isDisplayed());
    }

    @Test
    public void taskWithoutTitle_creation_testMarketing()  {

        driver.get("https://login2.nextbasecrm.com/");
//As a Marketing user go to  the main page of the website
        CRM_Utilities.crm_login(driver, "marketing40@cydeo.com", "UserUser");
//I'm able to see the dashboard
        WebElement taskTab = driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']"));
        Assert.assertTrue(taskTab.isDisplayed());

//Click to the 'TASK' tab in the upper part of the webpage
        taskTab.click();

        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id=\"bx-html-editor-iframe-cnt-lifefeed_task_form\"]/iframe")));
        WebElement taskBody= driver.findElement(By.xpath("//html/body[@style='min-height: 84px;']"));
        driver.switchTo().parentFrame();

//Click 'SEND' button
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();

//Verify “The task name is not specified.” massage is displayed.
        WebElement message =driver.findElement(By.xpath("//div[.='The task name is not specified.']"));
        Assert.assertTrue(message.isDisplayed());
    }

    @Test
    public void taskWithoutTitle_creation_testHelpDesk()  {

        driver.get("https://login2.nextbasecrm.com/");
//As a HelpDesk user go to  the main page of the website
        CRM_Utilities.crm_login(driver, "helpdesk40@cydeo.com", "UserUser");
//I'm able to see the dashboard
        WebElement taskTab = driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']"));
        Assert.assertTrue(taskTab.isDisplayed());

//Click to the 'TASK' tab in the upper part of the webpage
        taskTab.click();

        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id=\"bx-html-editor-iframe-cnt-lifefeed_task_form\"]/iframe")));
        WebElement taskBody= driver.findElement(By.xpath("//html/body[@style='min-height: 84px;']"));
        driver.switchTo().parentFrame();

//Click 'SEND' button
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();

//Verify “The task name is not specified.” massage is displayed.
        WebElement message =driver.findElement(By.xpath("//div[.='The task name is not specified.']"));
        Assert.assertTrue(message.isDisplayed());

    }
}
