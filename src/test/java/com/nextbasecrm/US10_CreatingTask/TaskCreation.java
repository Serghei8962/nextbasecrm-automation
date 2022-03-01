package com.nextbasecrm.US10_CreatingTask;

import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TaskCreation {
    public WebDriver driver;

    @BeforeMethod
    public  void driverSetup() {
        String browserType = ConfigurationReader.getProperty("browser");
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
    }

    @Test
    public void task_creation_testHR()  {

//As an HR user go to  the main page of the website

        CRM_Utilities.crm_login(driver, ConfigurationReader.properties.getProperty("username.hr"), ConfigurationReader.properties.getProperty("password"));

//I'm able to see the dashboard
        WebElement taskTab = driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']"));
        Assert.assertTrue(taskTab.isDisplayed());

//Click to the 'TASK' tab in the upper part of the webpage
        taskTab.click();

//Navigate to the "Things to go" input box and pass the title of the task
        WebElement taskTitle= driver.findElement(By.xpath("//input[@data-bx-id='task-edit-title']"));
        taskTitle.sendKeys("This is the title");

//Pass the task description into the empty task body below the task title
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id=\"bx-html-editor-iframe-cnt-lifefeed_task_form\"]/iframe")));
        WebElement taskBody= driver.findElement(By.xpath("//html/body[@style='min-height: 84px;']"));
        taskBody.sendKeys("These are the task details");
        driver.switchTo().parentFrame();

//Click 'SEND' button
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();

//Verify “Task has been created” popup is displayed.
        WebElement popup =driver.findElement(By.xpath("//div[@id='blogPostEditCreateTaskPopup_content']"));
        Assert.assertTrue(popup.isDisplayed());

    }

    @Test
    public void task_creation_testHelpDesk()  {

//As a HelpDesk user go to  the main page of the website
        CRM_Utilities.crm_login(driver, ConfigurationReader.properties.getProperty("username.helpdesk"), ConfigurationReader.properties.getProperty("password"));
//I'm able to see the dashboard
        WebElement taskTab = driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']"));
        Assert.assertTrue(taskTab.isDisplayed());

//Click to the 'TASK' tab in the upper part of the webpage
        taskTab.click();

//Navigate to the "Things to go" input box and pass the title of the task
        WebElement taskTitle= driver.findElement(By.xpath("//input[@data-bx-id='task-edit-title']"));
        taskTitle.sendKeys("This is the title");

//Pass the task description into the empty task body below the task title
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id=\"bx-html-editor-iframe-cnt-lifefeed_task_form\"]/iframe")));
        WebElement taskBody= driver.findElement(By.xpath("//html/body[@style='min-height: 84px;']"));
        taskBody.sendKeys("These are the task details");
        driver.switchTo().parentFrame();

//Click 'SEND' button
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();

//Verify “Task has been created” popup is displayed.
        WebElement popup =driver.findElement(By.xpath("//div[@id='blogPostEditCreateTaskPopup_content']"));
        Assert.assertTrue(popup.isDisplayed());

    }

    @Test
    public void task_creation_testMarketing()  {

//As a Marketing user go to  the main page of the website
        CRM_Utilities.crm_login(driver, ConfigurationReader.properties.getProperty("username.marketing"), ConfigurationReader.properties.getProperty("password"));
//I'm able to see the dashboard
        WebElement taskTab = driver.findElement(By.xpath("//span[@id='feed-add-post-form-tab-tasks']"));
        Assert.assertTrue(taskTab.isDisplayed());

//Click to the 'TASK' tab in the upper part of the webpage
        taskTab.click();

//Navigate to the "Things to go" input box and pass the title of the task
        WebElement taskTitle= driver.findElement(By.xpath("//input[@data-bx-id='task-edit-title']"));
        taskTitle.sendKeys("This is the title");

//Pass the task description into the empty task body below the task title
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@id=\"bx-html-editor-iframe-cnt-lifefeed_task_form\"]/iframe")));
        WebElement taskBody= driver.findElement(By.xpath("//html/body[@style='min-height: 84px;']"));
        taskBody.sendKeys("These are the task details");
        driver.switchTo().parentFrame();

//Click 'SEND' button
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();

//Verify “Task has been created” popup is displayed.
        WebElement popup =driver.findElement(By.xpath("//div[@id='blogPostEditCreateTaskPopup_content']"));
        Assert.assertTrue(popup.isDisplayed());

    }

@AfterMethod
        public void tearDown(){
        driver.close();
    }
}
