package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class US13 {
    // refactor the java class
    WebDriver driver;
    @BeforeMethod
    public void setupMethod(){
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver,ConfigurationReader.getProperty("username"),"UserUser");
    }
    @Test
    public void TC1_Users_send_message_successfully(){
        //user click the more tab
        WebElement moreBtn = driver.findElement(By.xpath("//span[.='More']//span[@class='feed-add-post-more-icon']"));
        moreBtn.click();
        BrowserUtils.sleep(2);
        //user click the Appreciation button
        WebElement appreciationBtn = driver.findElement(By.xpath("(//span[.='Appreciation'])[1]"));
        try {
            appreciationBtn.click();
        }catch (NoSuchElementException e){
            moreBtn.click();
            appreciationBtn.click();
        }


        //switch to iframe for write something on the message body
        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='bx-editor-iframe']"));
        driver.switchTo().frame(iframe);

        BrowserUtils.sleep(2);
        //writing something to do message body
        WebElement messageBody = driver.findElement(By.xpath("//body[@contenteditable='true']"));
        messageBody.sendKeys("Appreciation Message");

        //switching back to the parent frame
        driver.switchTo().parentFrame();

        //click send button
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();
    }
    @Test
    public void TC2_Users_get_errorMessageTitle() {
        //click message tab
        WebElement messageTab = driver.findElement(By.id("feed-add-post-form-tab-message"));
        messageTab.click();
        //click send button
        WebElement sendBtn = driver.findElement(By.xpath("//button[@id='blog-submit-button-save']"));
        sendBtn.click();
        //display "The message title is not specified"
        WebElement ErrorMessageTitle = driver.findElement(By.cssSelector("div[class='feed-add-error']"));
        System.out.println("messageTitle.isDisplayed() = " + ErrorMessageTitle.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
