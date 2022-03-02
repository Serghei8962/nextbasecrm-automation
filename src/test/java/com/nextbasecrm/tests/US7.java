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

public class US7 {
    WebDriver driver;

    @BeforeMethod
    public void setupMethod() {
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver, ConfigurationReader.getProperty("username"), "UserUser");
    }

    @Test
    public void one_answer_for_a_poll() {
        //finding CRM button to refresh the page, so it can take me the homepage without issue
        WebElement CRM24_linkButton = driver.findElement(By.xpath("//a[@id='logo_24_a']"));
        CRM24_linkButton.click();
        //Writing "111222333WHICHPROGRAMMINGLANGUAGETOUSE333222111" in the search box and finding existing poll
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='search-textbox-input']"));
        searchInput.sendKeys("111222333WHICHPROGRAMMINGLANGUAGETOUSE333222111" + Keys.ENTER);

        //finding vote and vote again buttons
        WebElement voteBtn = driver.findElement(By.xpath("//button[.='Vote']"));
        WebElement voteAgainBtn = driver.findElement(By.xpath("//button[.='Vote again']"));

        //if vote again button is displayed, click on it to get vote button
        if (voteAgainBtn.isDisplayed()) {
            voteAgainBtn.click();
        }

        //finding java and python locators for clicking
        WebElement javaClick = driver.findElement(By.xpath("//label[.='JAVA']"));
        WebElement pythonClick = driver.findElement(By.xpath("//label[.='PYTHON']"));

        //finding java and python other locator for isSelected() method
        WebElement javaSelected = driver.findElement(By.id("vote_radio_1174_2632"));
        WebElement pythonSelected = driver.findElement(By.id("vote_radio_1174_2633"));

        //clicking java option and verifying java is selected and python is not selected
        javaClick.click();
        Assert.assertEquals(javaSelected.isSelected(), !(pythonSelected.isSelected()), "Java is NOT selected");

        //clicking python option and verifying python is selected and java is not selected
        pythonClick.click();
        Assert.assertEquals(pythonSelected.isSelected(), !(javaSelected.isSelected()), "Python is NOT selected");

        //after selecting python option,will click to the vote button
        voteBtn.click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}