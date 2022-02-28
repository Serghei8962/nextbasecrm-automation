package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US5 {

    public WebDriver driver;

    //made w sergiu on zoom
    /*
    @BeforeMethod
    public void setupMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver);
    }
     */



    //code made by hulya
    public void setupMethod(){
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver,ConfigurationReader.getProperty("username"),"UserUser");
    }




    //made w sergiu on zoom
    /*
    @Test
    public void write_the_message(){

        //1. When users click the MASSAGE tab, they should be able to write the message body and
        WebElement messageButton = driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-tab-message\"]/span"));
        messageButton.click();

        //WebElement messageBox = driver.findElement(By.xpath("//*[@id=\"microoPostFormLHE_blogPostForm_inner\"]/span[1]"));
        //messageBox.click();
        BrowserUtils.sleep(3);

        WebElement enterMessage = driver.findElement(By.xpath("//*[@id=\"bxed_idPostFormLHE_blogPostForm\"]"));
        enterMessage.sendKeys("hello world");


        //send a message successfully to the feed.
        //
        //2. “The message title is not specified”. Should be displayed when users send a message
        //without a content.

    }
    */


    //code made by hulya
    @Test
    public void TC1_Users_get_errorMessageTitle() {

        //click message tab
        WebElement messageTab = driver.findElement(By.id("feed-add-post-form-tab-message"));
        messageTab.click();

        //click send button
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();

        //display "The message title is not specified"
        WebElement messageTitle = driver.findElement(By.cssSelector("div[class='feed-add-error']"));
        System.out.println("messageTitle.isDisplayed() = " + messageTitle.isDisplayed());

    }


    //code made by hulya
    @Test
    public void TC2_Users_send_message_successfully(){

        //click message tab
        WebElement messageTab = driver.findElement(By.id("feed-add-post-form-tab-message"));
        messageTab.click();

        //write message in the message body
        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']"));
        driver.switchTo().frame(iframe);

        driver.findElement(By.xpath("//body[@contenteditable='true']")).sendKeys("Hello from Marius");
        driver.switchTo().parentFrame();

        //click send button
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();

    }

/*
    @AfterMethod
    public void tearDown() {
        driver.close();
    }
 */



}
